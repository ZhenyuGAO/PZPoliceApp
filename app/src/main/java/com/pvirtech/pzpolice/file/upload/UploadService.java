package com.pvirtech.pzpolice.file.upload;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.pvirtech.pzpolice.file.down.DownloadInfo;
import com.pvirtech.pzpolice.file.down.DownloadManager;
import com.pvirtech.pzpolice.main.AppValue;
import com.pvirtech.pzpolice.utils.L;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UploadService extends Service {
    private String TAG = "DownloadService";
    private int THREAD_COUNT = 1;
    private ExecutorService fixedThreadPool = null;
    private OkHttpClient client = new OkHttpClient();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DownloadInfo downloadInfo = new DownloadInfo("http://download.apk8.com/d2/soft/meilijia.apk", AppValue.getInstance()
                .getImgPath(), "aa",
                "apk1.apk", 0, 0, 0, 0);
        DownloadManager.getInstance().addTask(downloadInfo);
        DownloadInfo downloadInfo2 = new DownloadInfo("http://download.apk8.com/d2/soft/guoranfangbian.apk", AppValue.getInstance()
                .getImgPath(), "aa",
                "apk2", 0, 0, 0, 0);
        DownloadManager.getInstance().addTask(downloadInfo2);
        DownloadInfo downloadInfo3 = new DownloadInfo("http://download.apk8.com/d2/soft/GGzhushou.apk", AppValue.getInstance()
                .getImgPath(), "aa",
                "apk3", 0, 0, 0, 0);
        DownloadManager.getInstance().addTask(downloadInfo3);
        fixedThreadPool = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            fixedThreadPool.execute(upLoadThread);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     *
     */
    private Thread upLoadThread = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    DownloadInfo downloadInfo = DownloadManager.getInstance().removeTask();
                    if (downloadInfo == null) {
                        Thread.sleep(2 * 1000);
                        continue;
                    }
                    uploadMultiFile();
//                    put(null, null);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    /**
     * 上传文件到服务器
     */
    private void uploadMultiFile() {
        final String url = "upload url";
        File file = new File("fileDir", "test.jpg");
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "test.jpg", fileBody)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
//        okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                L.d("sucess");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param mediaType
     * @param uploadInfo
     * @throws IOException
     */
    public void put(MediaType mediaType, UploadInfo uploadInfo) throws IOException {
        File file = new File(uploadInfo.getTargetPath());
        RequestBody body = RequestBody.create(mediaType, file);
        Request request = new Request.Builder()
                .url(uploadInfo.getUrl())
                .put(body)
                .build();
        Response response = client.newCall(request).execute();
        /*if (response.isSuccessful()) {
            L.d("sucess");
            InputStream inputStream = null;
            OutputStream outputStream = null;
            ResponseBody responseBody = response.body();
            long fileSize = responseBody.contentLength();
            L.d(TAG, "WriteFileManager.writeResponseBodyToDisk.fileSize:" + fileSize);
            try {
                byte[] fileReader = new byte[1024 * 1024];
                long fileSizeDownloaded = 0;
//                inputStream = responseBody.byteStream();
//                outputStream = new FileOutputStream(file);
                while (uploadInfo.getState() == FileState.DOING) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    L.i(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                L.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                outputStream.flush();
                uploadInfo.setState(FileState.FINISH);
            } catch (IOException e) {
                L.d("IOException", "IOException");
                uploadInfo.setState(FileState.ERROR);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }*/
    }


}