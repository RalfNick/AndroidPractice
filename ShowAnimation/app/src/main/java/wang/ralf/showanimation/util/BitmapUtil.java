package wang.ralf.showanimation.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Base64;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author lixiyuan
 * @date 2018/8/16
 */

public class BitmapUtil {
    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm      需要旋转的图片
     * @param degree  旋转角度
     * @param recycle 是否回收原图片
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree, boolean recycle) {
        if (bm == null) {
            return null;
        }
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (recycle && bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm         需要旋转的图片
     * @param scaleRatio 缩放比例
     * @param recycle    是否回收原图片
     * @return 旋转后的图片
     */
    public static Bitmap scaleBitmap(Bitmap bm, float scaleRatio, boolean recycle) {
        if (bm == null) {
            return null;
        }

        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postScale(scaleRatio, scaleRatio, bm.getWidth() / 2, bm.getHeight() / 2);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        if (returnBm == null) {
            returnBm = bm;
        }

        if (recycle && bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bitmap            需要旋转的图片
     * @param orientationDegree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int orientationDegree) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(orientationDegree, (float) bitmap.getWidth() / 2,
                (float) bitmap.getHeight() / 2);
        float targetX, targetY;
        if (orientationDegree == 90) {
            targetX = bitmap.getHeight();
            targetY = 0;
        } else {
            targetX = bitmap.getHeight();
            targetY = bitmap.getWidth();
        }
        final float[] values = new float[9];
        matrix.getValues(values);
        float x1 = values[Matrix.MTRANS_X];
        float y1 = values[Matrix.MTRANS_Y];
        matrix.postTranslate(targetX - x1, targetY - y1);
        Bitmap canvasBitmap = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getWidth(),
                Bitmap.Config.RGB_565);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(canvasBitmap);
        canvas.drawBitmap(bitmap, matrix, paint);
        return canvasBitmap;
    }


    /**
     * 通过降低图片的质量来压缩图片
     *
     * @param bitmap  要压缩的图片
     * @param maxSize 压缩后图片大小的最大值,单位KB
     * @return 压缩后的图片
     */
    public static Bitmap compressByQuality(Bitmap bitmap, int maxSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 70;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
//        Log.i("bitmapHelper", "图片压缩前大小：" + baos.toByteArray().length
//                + "byte");
        while (baos.toByteArray().length / 1024 > maxSize && quality > 0) {
            quality -= 10;
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
//            Log.i("bitmapHelper",
//                    "质量压缩到原来的" + quality + "%时大小为：" + baos.toByteArray().length
//                            + "byte");
        }
//        Log.i("bitmapHelper", "图片压缩后大小：" + baos.toByteArray().length
//                + "byte");
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
                baos.toByteArray().length);
        // bitmap.getConfig().
        return bitmap;
    }

    /**
     * 功能
     *
     * @param bitmap       要压缩图片
     * @param targetWidth  缩放的目标宽度
     * @param targetHeight 缩放的目标高度
     * @return 缩放后的图片
     */
    public static Bitmap compressBySize(Bitmap bitmap, int targetWidth,
                                        int targetHeight) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
                baos.toByteArray().length, opts);
        // 得到图片的宽度、高度；
        int imgWidth = opts.outWidth;
        int imgHeight = opts.outHeight;
        // 分别计算图片宽度、高度与目标宽度、高度的比例；取大于该比例的最小整数；
        int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
        int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
        if (widthRatio > 1 && widthRatio > 1) {
            if (widthRatio > heightRatio) {
                opts.inSampleSize = widthRatio;
            } else {
                opts.inSampleSize = heightRatio;
            }
        }
        // 设置好缩放比例后，加载图片进内存；
        opts.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
                baos.toByteArray().length, opts);
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap decodeFile(String filePath) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 4;

        return BitmapFactory.decodeFile(filePath, opts);
    }

    /**
     * 解压目标大小的图片
     *
     * @param filePath filePath
     * @param width    width
     * @param height   height
     * @return bitmap
     */
    public static Bitmap decodeFile(String filePath, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        float sampleSize = 1;
        if (options.outWidth > width || options.outHeight > height) {
            sampleSize = Math.max(options.outWidth * 1f / width, options.outHeight * 1f / height);
        }
        options.inSampleSize = Math.round(sampleSize) / 3;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(filePath, options);
    }


    /**
     * 保存图片
     *
     * @param bitmap
     * @param path
     * @param quality
     * @return
     */
    public static boolean saveBitmap(Bitmap bitmap, String path, Bitmap.CompressFormat compressFormat, int quality) {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(path));
            if (bitmap.compress(compressFormat, quality, bos)) {
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 缩放图片到指定宽高
     *
     * @param bm        所要转换的bitmap
     * @param newWidth  新的宽
     * @param newHeight 新的高
     * @return 指定宽高的bitmap
     */
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    /**
     * 判断图片的角度是否正确，加以调整，并将调整后的图片路径返回
     *
     * @param sourcePath  原始图片的路径
     * @param correctPath 调整后图片的输出路径
     * @return
     */
    public static String makeBitmapToCorrectDegree(String sourcePath, String correctPath) {
        int bitmapDegree = getBitmapDegree(sourcePath);
        Bitmap bitmap = BitmapFactory.decodeFile(sourcePath);
        if (bitmap != null) {
            bitmap = rotateBitmapByDegree(bitmap, bitmapDegree, true);

            FileOutputStream b = null;
            try {
                b = new FileOutputStream(correctPath);
                // 把数据写入文件
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (b != null) {
                        b.flush();
                        b.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bitmap.recycle();
            return correctPath;
        } else {
            return null;
        }
    }

    /**
     * Bitmap 转换成 ByteArray格式
     *
     * @param bmp
     * @param needRecycle
     * @return
     */
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        int i;
        int j;
        if (bmp.getHeight() > bmp.getWidth()) {
            i = bmp.getWidth();
            j = bmp.getWidth();
        } else {
            i = bmp.getHeight();
            j = bmp.getHeight();
        }

        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);

        while (true) {
            localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0, i, j), null);
            if (needRecycle) {
                bmp.recycle();
            }
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                //F.out(e);
            }
            i = bmp.getHeight();
            j = bmp.getHeight();
        }
    }

    /**
     * Bitmap转换成byte[]并且进行压缩,压缩到不大于maxkb
     *
     * @param bitmap bitmap
     * @param maxKb  maxKb
     * @return byte[]
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int maxKb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > maxKb && options != 10) {
            output.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);
            options -= 10;
        }
        return output.toByteArray();
    }

    /**
     * Bitmap转换成byte[]
     *
     * @param bitmap bitmap
     * @return byte[]
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
        return output.toByteArray();
    }

    /**
     * 缩放图片
     *
     * @param context
     * @param resId
     * @param wantWidth
     * @param wantHeight
     * @return
     */
    public static Bitmap getScaledBitmap(Context context, int resId, int wantWidth, int wantHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, options);

        int inSampleSize = 1;

        int width = options.outWidth;
        int height = options.outHeight;

        if (width > wantWidth || height > wantHeight) {
            int halfWidth = width / 2;
            int halfHeight = height / 2;
            while (halfWidth / inSampleSize >= wantWidth && halfHeight / inSampleSize >= wantHeight) {
                inSampleSize *= 2;
            }
        }

        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(context.getResources(), resId, options);
    }

    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url
     * @return
     */
    public static Bitmap getBitmapFromUrl(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }

    /**
     * 把Bitmap对象转为Base64的String
     *
     * @return
     */
    public static String transBitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取本地媒体的Base64字符串
     *
     * @param path
     * @return
     */
    public static String transFilePathToBase64(String path) {
        String result = "";
        ByteArrayOutputStream baos = null;
        FileInputStream fis = null;
        byte[] bytes = null;
        try {
            if (path != null) {
                bytes = new byte[1024];
                fis = new FileInputStream(path);
                baos = new ByteArrayOutputStream();
                while (fis.read(bytes) != -1) {
                    baos.write(bytes, 0, bytes.length);
                }
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 下载失败与获取失败时都统一显示默认下载失败图片
     *
     * @return
     */
    public static Bitmap getBitmapFromDrawableRes(Context context, int res) {
        try {
            return getBitmapImmutableCopy(context.getResources(), res);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final Bitmap getBitmapImmutableCopy(Resources res, int id) {
        return getBitmap(res.getDrawable(id)).copy(Bitmap.Config.RGB_565, false);
    }

    public static final Bitmap getBitmap(Drawable dr) {
        if (dr == null) {
            return null;
        }

        if (dr instanceof BitmapDrawable) {
            return ((BitmapDrawable) dr).getBitmap();
        }
        return null;
    }

    public static boolean isInvalidPictureFile(String mimeType) {
        String lowerCaseFilepath = mimeType.toLowerCase();
        return (lowerCaseFilepath.contains("jpg") || lowerCaseFilepath.contains("jpeg")
                || lowerCaseFilepath.toLowerCase().contains("png") || lowerCaseFilepath.toLowerCase().contains("bmp") || lowerCaseFilepath
                .toLowerCase().contains("gif"));
    }

    public static boolean isGif(String extension) {
        return !TextUtils.isEmpty(extension) && extension.toLowerCase().equals("gif");
    }

}
