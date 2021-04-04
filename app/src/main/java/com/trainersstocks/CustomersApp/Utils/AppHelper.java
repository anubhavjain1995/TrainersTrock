package com.trainersstocks.CustomersApp.Utils;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.ImageView;

import com.trainersstocks.CustomersApp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textfield.TextInputLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class AppHelper {

    private static long refid = 8201801;

    public static void setupLoadMore(RecyclerView recyclerView, final OnScrollToEnd listener) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItem == totalItemCount - 1) {
                    listener.scrolledToEnd(lastVisibleItem);
                }
            }
        });
    }


    private static float density;

    public static int dp(float value, Context context) {
        density = context.getResources().getDisplayMetrics().density;
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }

    public interface OnScrollToEnd {
        void scrolledToEnd(int lastVisibleItem);

    }


    public static void setImage(Context context, ImageView imageView, String image, Integer placeholder) {
        loadImage(context, imageView, image, placeholder);
    }

    public static void setImage(Context context, ImageView imageView, String image) {
        loadImage(context, imageView, image, R.color.colorTransparent);
    }

    private static void loadImage(Context context, ImageView imageView, String image, Integer placeholder) {
        try {
            Glide.with(context).applyDefaultRequestOptions(
                    new RequestOptions()
                            .placeholder(placeholder)
                            .error(placeholder)
            ).load(image).into(imageView);
        } catch (Exception e) {
            //if (D) Log.e(TAG, "Exception : " + e.getMessage());
        }
    }

    public static void setImage(Context context, ImageView imageView, int image) {
        try {
            Glide.with(context).applyDefaultRequestOptions(
                    new RequestOptions()
                            .placeholder(null)
                            .error(null)
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            ).load(image).transition(withCrossFade()).into(imageView);
        } catch (Exception e) {
//            if (D) Log.e(TAG, "Exception : " + e.getMessage());

        }
    }

    public static void setImage(Context context, CircleImageView imageView, int image) {
        try {
            Glide.with(context).applyDefaultRequestOptions(
                    new RequestOptions()
                            .placeholder(null)
                            .error(null)
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            ).load(image).transition(withCrossFade()).into(imageView);
        } catch (Exception e) {
//            if (D) Log.e(TAG, "Exception : " + e.getMessage());

        }
    }

/*

    public static void shareLink(Context context, VideoModel model) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "LookChup Videos");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "LookChup Videos\n" + model.getPostFile());
            context.startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }
*/

    public static void vibrate(Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(300);
        }
    }


    public static boolean Validate(String txt, String err, TextInputLayout textInputLayout) {
        if (txt == null || txt.isEmpty()) {
            textInputLayout.setError(err);
            return false;
        } else {

            textInputLayout.setError(null);
            return true;
        }

    }

    public static boolean ValidatePhone(String txt, String err, TextInputLayout textInputLayout) {
        if (txt == null || txt.isEmpty() || txt.length() < 9 || txt.length() > 13) {
            textInputLayout.setError(err);
            return false;
        } else {

            textInputLayout.setError(null);
            return true;
        }

    }


    public static boolean ValidateEmail(String txt, String err, TextInputLayout textInputLayout) {
        if (txt == null || txt.isEmpty() || !txt.contains(".") || !txt.contains("@")) {
            textInputLayout.setError(err);
            return false;
        } else {
            textInputLayout.setError(null);
            return true;
        }

    }

    public static boolean ValidateTextN(Context context, String txt) {
        if (txt == null || txt.isEmpty()) {
            Toasty.error(context, "Please set date of birth", Toasty.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }

    }


}
