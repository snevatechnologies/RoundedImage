# RoundedImage

===============

A fast rounded image view library developed by Sneva Technologies to help developers to create rounded image

![RoundedImage](https://github.com/snevatechnologies/RoundedImage/blob/master/photo_6220028383631356360_y.jpg)

You can use any library like Glide or Picasso to show image in this RoundedImage

Gradle
------
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
```
dependencies {
	...
	 implementation 'com.github.snevatechnologies:RoundedImage:1.0'
}
```

Usage
-----
```xml
<com.sneva.roundedview.RoundedImage
        android:id="@+id/roundedImage"
        android:layout_width="200dp"
        android:layout_height="200dp"
        app:rimage_corner_radius="18dp"
        android:src="@drawable/placeholder" />
```

Features
-----------
* You can set border color or width
* You can set custom radius
