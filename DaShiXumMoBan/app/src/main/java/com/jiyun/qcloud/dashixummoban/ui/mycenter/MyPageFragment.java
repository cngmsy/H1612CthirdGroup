package com.jiyun.qcloud.dashixummoban.ui.mycenter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.main.LoginActivity;
import com.jiyun.qcloud.dashixummoban.main.Shang;
import com.jiyun.qcloud.dashixummoban.main.Shangchuang;
import com.jiyun.qcloud.dashixummoban.main.UserBean;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 */
public class MyPageFragment extends BaseFragment {

    @BindView(R.id.ima_head)
    ImageView imaHead;
    @BindView(R.id.textlogin)
    TextView textlogin;
    Unbinder unbinder;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_page;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
    }

    @Override
    public void setBundle(Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ima_head, R.id.textlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ima_head:
                setData();
                break;
            case R.id.textlogin:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                if (uri != null) {
                    // Log.e("相册上传", uri + "");
                    crop(uri);
                    Glide.with(getActivity().getApplicationContext()).load(uri).asBitmap().centerCrop().into(new BitmapImageViewTarget(imaHead) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                            ciDrawable.setCircular(true);
                            imaHead.setImageDrawable(ciDrawable);
                        }
                    });
                }
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            if (data != null) {
                // 得到图片的全路径
                Bitmap bitmap = data.getParcelableExtra("data");
                final Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null));
                Log.e("拍照上传--REQUEST_CUT", uri + "");
                Glide.with(getActivity().getApplicationContext()).load(uri).asBitmap().centerCrop().into(new BitmapImageViewTarget(imaHead) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                        ciDrawable.setCircular(true);
                        imaHead.setImageDrawable(ciDrawable);
                    }
                });

                //头像上传操作
                HashMap<String, String> map = new HashMap<>();
                final File file = saveBitmapFile(bitmap);
                map.put("file", file + "");
                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS);


                OkHttpClient mOkHttpClient = builder.build();

                MultipartBody.Builder mbody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                mbody.addFormDataPart("image", file.getName(), RequestBody.create(null, file));
                RequestBody requestBody = mbody.build();
                Request request = new Request.Builder()
                        .url("http://123.206.14.104:8080/FileUploadDemo/FileUploadServlet")
                        .post(requestBody)
                        .build();
                mOkHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Gson gson = new Gson();
                        Shangchuang shangchuang = gson.fromJson(string, Shangchuang.class);
                        final Shang shangchuang2 = gson.fromJson(shangchuang.getData(), Shang.class);
                        SharedPreferences.Editor zc = getActivity().getSharedPreferences("zc", Context.MODE_PRIVATE).edit();
                        zc.putString("picture", "http://123.206.14.104:8080/FileUploadDemo/files/a.jpg");
                        zc.apply();

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String url = shangchuang2.getUrl();
                                Log.e("url", url);
                                Toast.makeText(getActivity(), shangchuang2.getResult(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            if (!data.equals("")) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap bitmap = (Bitmap) extras.get("data");
                    //拿到bitmap，做喜欢做的事情把  ---> 显示 or上传？
                    Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null));
                    // Log.e("拍照上传--REQUEST_CAREMA", uri + "");
                    //myfragment_touxiang.setImageBitmap(bitmap);
                    Glide.with(getActivity().getApplicationContext()).load(uri).asBitmap().centerCrop().into(new BitmapImageViewTarget(imaHead) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                            ciDrawable.setCircular(true);
                            imaHead.setImageDrawable(ciDrawable);
                        }
                    });
                }
            }
        }
    }

    private File saveBitmapFile(Bitmap bitmap) {
        File file = new File("/sdcard/01.jpg");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    private void setData() {
        final PopupWindow popupWindow = new PopupWindow();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.mypop, null);
        TextView change_tx = (TextView) view.findViewById(R.id.change_tx);
        TextView look_big = (TextView) view.findViewById(R.id.look_big);
        TextView qux = (TextView) view.findViewById(R.id.qux);

        popupWindow.setContentView(view);
        popupWindow.setHeight(400);
        popupWindow.setWidth(600);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.showAtLocation(getActivity().findViewById(R.id.textlogin), Gravity.CENTER | Gravity.CENTER, 0, 0);
        change_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                popupWindow.dismiss();
            }
        });
        look_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(captureIntent, PHOTO_REQUEST_CAREMA);
                popupWindow.dismiss();
            }
        });
        qux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(UserBean event) {
        String img = event.getUrl();
        String name = event.getName();

        Glide.with(getActivity().getApplicationContext()).load(img).asBitmap().centerCrop().into(new BitmapImageViewTarget(imaHead) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                ciDrawable.setCircular(true);
                imaHead.setImageDrawable(ciDrawable);
            }
        });
        textlogin.setText(name);
//        username.setVisibility(View.VISIBLE);
//        login.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
