# ColorAnimator
ColorAnimator is a color transition animation library for android

|firstColor|----delayTime----|transitToSecondColor|----delayTime----|transitToThirdColor| ...


<div style="text-align: center"><table><tr>
  <td style="text-align: center">
  <a href="https://twitter.com/BlueAquilae/status/1049315328835182592">
    <img src="https://github.com/RezaKardoost/ColorAnimator/blob/master/shots/device20181121235248.gif" width="200"/></a>
</td>
<td style="text-align: center">
  <a href="https://twitter.com/BlueAquilae/status/1018208010643103744">
<img src="https://github.com/RezaKardoost/ColorAnimator/blob/master/shots/device20181121235134.gif" width="200"/>
  </a>
</tr></table></div>


# Setup
#### Step #1. Add the JitPack repository to root build.gradle file:

```gradle
allprojects {
    repositories {
	...
	maven { url "https://jitpack.io" }
    }
}
```

#### Step #2. Add the dependency

```groovy
dependencies {
    implementation 'com.github.RezaKardoost:ColorAnimator:v1.1'

}
```

# Implementation

```kotlin
<ir.beigirad.zigzagview.ZigzagView
    android:layout_width="match_parent"
    android:layout_height="240dp"
    app:zigzagBackgroundColor="#8bc34a"
    app:zigzagElevation="8dp"
    app:zigzagHeight="10dp"
    app:zigzagShadowAlpha="0.9"
    app:zigzagSides="top|bottom"
    app:zigzagPaddingContent="16dp">
    
    // add child view(s)
    
</ir.beigirad.zigzagview.ZigzagView>
```

## Usage

```kotlin
var ca: ColorAnimator? = null

//onCreateView
ca = ColorAnimator.Builder(colors)
                .setAnimationDuration(5000)
                .setDelayDuration(10000)
                .setRepeatMode(true)//default:false
                .addColorUpdateListener(object : ColorAnimator.ColorAnimatorUpdateListener{
                    override fun onColorUpdate(color: Int) {
                        //change your view's textColor or backgroundColor or ...
                    }

                })
                .build()
        ca?.start()

//onPause
ca?.pause()

//onResume
ca?.resume()

```
