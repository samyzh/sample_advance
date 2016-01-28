package com.zsy.frame.sample.jni.control.advance.designmode.creational.builder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

/**
 * 建造者模式包含如下角色：
 * Builder：抽象建造者
 * ConcreteBuilder：具体建造者
 * Director：指挥者
 * Product：产品角色
 *
 * 省略抽象建造者角色：如果系统中只需要一个具体建造者的话，可以省略掉抽象建造者
 * 省略指挥者角色：在具体建造者只有一个的情况下，如果抽象建造者角色已经被省略掉，那么还可以省略指挥者角色，让Builder角色扮演指挥者与建造者双重角色
 */
public class MaterialSimpleListItem {

  private Builder mBuilder;

  private MaterialSimpleListItem(Builder builder) {
    mBuilder = builder;
  }

  public Drawable getIcon() {
    return mBuilder.mIcon;
  }

  public CharSequence getContent() {
    return mBuilder.mContent;
  }

  //* 省略抽象建造者角色：如果系统中只需要一个具体建造者的话，可以省略掉抽象建造者
  //* 省略指挥者角色：在具体建造者只有一个的情况下，如果抽象建造者角色已经被省略掉，那么还可以省略指挥者角色，让Builder角色扮演指挥者与建造者双重角色
  public static class Builder {
    //Product：产品角色
    private Context mContext;
    protected Drawable mIcon;

    protected CharSequence mContent;

    public Builder(Context context) {
      mContext = context;
    }

    public Builder icon(Drawable icon) {
      this.mIcon = icon;
      return this;
    }

    public Builder icon(@DrawableRes int iconRes) {
      if (iconRes == 0) return this;
      return icon(ContextCompat.getDrawable(mContext, iconRes));
    }

    public Builder content(CharSequence content) {
      this.mContent = content;
      return this;
    }

    public Builder content(@StringRes int contentRes) {
      return content(mContext.getString(contentRes));
    }

    public MaterialSimpleListItem build() {
      return new MaterialSimpleListItem(this);
    }
  }

  @Override public String toString() {
    if (getContent() != null) {
      return getContent().toString();
    } else {
      return "(no content)";
    }
  }
}
