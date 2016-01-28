package com.packtpub.apps.rxjava_essentials.chapter7;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.packtpub.apps.rxjava_essentials.R;
import com.rey.material.widget.Button;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import timber.log.Timber;

public class NetworkTaskFragment extends Fragment {

    @InjectView(R.id.arc_progress)
    ArcProgress mArcProgress;

    @InjectView(R.id.button_download)
    Button mButton;

    private PublishSubject<Integer> mDownloadProgress = PublishSubject.create();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_download, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }

    @OnClick(R.id.button_download)
    void download() {
        mButton.setText(getString(R.string.downloading));
        mButton.setClickable(false);

        mDownloadProgress
            .distinct()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Integer>() {
                @Override
                public void onCompleted() {
                    Timber.d("Completed");
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e(e.getMessage());
                }

                @Override
                public void onNext(Integer progress) {
                    mArcProgress.setProgress(progress);
                }
            });

        String destination = "/sdcard/softboy.avi";

        obserbableDownload("http://archive.blender.org/fileadmin/movies/softboy.avi", destination)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(success -> {
                resetDownloadButton();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                File file = new File(destination);
                intent.setDataAndType(Uri.fromFile(file), "video/avi");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }, error -> {
                Toast.makeText(getActivity(), "Something went south", Toast.LENGTH_SHORT).show();
                resetDownloadButton();
            });
    }

    private void resetDownloadButton() {
        mButton.setText(getString(R.string.download));
        mButton.setClickable(true);
        mArcProgress.setProgress(0);
    }

    private Observable<Boolean> obserbableDownload(String source, String destination) {
        return Observable.create(subscriber -> {
            try {
                boolean result = downloadFile(source, destination);
                if (result) {
                    subscriber.onNext(true);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new Throwable("Download failed."));
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    private boolean downloadFile(String source, String destination) {
        boolean result = false;
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(source);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return false;
            }

            int fileLength = connection.getContentLength();

            input = connection.getInputStream();
            output = new FileOutputStream(destination);

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;

                if (fileLength > 0) {
                    int percentage = (int) (total * 100 / fileLength);
                    mDownloadProgress.onNext(percentage);
                }
                output.write(data, 0, count);
            }
            mDownloadProgress.onCompleted();
            result = true;
        } catch (Exception e) {
            mDownloadProgress.onError(e);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                mDownloadProgress.onError(e);
            }

            if (connection != null) {
                connection.disconnect();
                mDownloadProgress.onCompleted();
            }
        }
        return result;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
