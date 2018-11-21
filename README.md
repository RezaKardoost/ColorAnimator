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
