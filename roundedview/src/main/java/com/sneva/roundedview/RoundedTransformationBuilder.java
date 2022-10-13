package com.sneva.roundedview;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;
import com.squareup.picasso.Transformation;
import java.util.Arrays;

public final class RoundedTransformationBuilder {

  private final DisplayMetrics mDisplayMetrics;

  private float[] mCornerRadii = new float[] { 0, 0, 0, 0 };

  private boolean mOval = false;
  private float mBorderWidth = 0;
  private ColorStateList mBorderColor =
      ColorStateList.valueOf(RoundedDrawable.DEFAULT_BORDER_COLOR);
  private ImageView.ScaleType mScaleType = ImageView.ScaleType.FIT_CENTER;

  public RoundedTransformationBuilder() {
    mDisplayMetrics = Resources.getSystem().getDisplayMetrics();
  }

  public RoundedTransformationBuilder scaleType(ImageView.ScaleType scaleType) {
    mScaleType = scaleType;
    return this;
  }

  public RoundedTransformationBuilder cornerRadius(float radius) {
    mCornerRadii[Corner.TOP_LEFT] = radius;
    mCornerRadii[Corner.TOP_RIGHT] = radius;
    mCornerRadii[Corner.BOTTOM_RIGHT] = radius;
    mCornerRadii[Corner.BOTTOM_LEFT] = radius;
    return this;
  }

  public RoundedTransformationBuilder cornerRadius(@Corner int corner, float radius) {
    mCornerRadii[corner] = radius;
    return this;
  }

  public RoundedTransformationBuilder cornerRadiusDp(float radius) {
    return cornerRadius(
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, mDisplayMetrics));
  }

  public RoundedTransformationBuilder cornerRadiusDp(@Corner int corner, float radius) {
    return cornerRadius(corner,
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, mDisplayMetrics));
  }

  public RoundedTransformationBuilder borderWidth(float width) {
    mBorderWidth = width;
    return this;
  }

  public RoundedTransformationBuilder borderWidthDp(float width) {
    mBorderWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, mDisplayMetrics);
    return this;
  }

  public RoundedTransformationBuilder borderColor(int color) {
    mBorderColor = ColorStateList.valueOf(color);
    return this;
  }

  public RoundedTransformationBuilder borderColor(ColorStateList colors) {
    mBorderColor = colors;
    return this;
  }

  public RoundedTransformationBuilder oval(boolean oval) {
    mOval = oval;
    return this;
  }

  public Transformation build() {
    return new Transformation() {
      @Override public Bitmap transform(Bitmap source) {
        Bitmap transformed = RoundedDrawable.fromBitmap(source)
            .setScaleType(mScaleType)
            .setCornerRadius(mCornerRadii[0], mCornerRadii[1], mCornerRadii[2], mCornerRadii[3])
            .setBorderWidth(mBorderWidth)
            .setBorderColor(mBorderColor)
            .setOval(mOval)
            .toBitmap();
        if (!source.equals(transformed)) {
          source.recycle();
        }
        return transformed;
      }

      @Override public String key() {
        return "r:" + Arrays.toString(mCornerRadii)
            + "b:" + mBorderWidth
            + "c:" + mBorderColor
            + "o:" + mOval;
      }
    };
  }
}
