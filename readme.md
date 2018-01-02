# GestureView--手势监听控件

> 一提到滑动冲突，是不是就脑壳痛？尝试写个控件用来处理这些问题~

刚刚开始，慢慢来。。

好像recyclerView的手势冲突比较麻烦？所以先做了个recyclerview的demo。有了这个GestureView似乎会方便很多..

这个demo在recycler上覆盖一个GestureView监听手势，并对header进行缩放动画。

侧滑删除啥的应该都可以用这个，之后再慢慢写慢慢改

由于还刚刚开始做，使用方法为将GestureView文件夹下的几个java文件拷贝到工程里。

![ezgif.com-video-to-gif](http://qiniu.zzzia.net/ezgif.com-video-to-gif.gif)



原理是自定义一个只监听事件，不消费事件的继承自FrameLayout的控件。
在这个控件实现OnGestureListener接口并把接口方法单独暴露出去定制。
内置缩放，平移等操作（目前只有缩放），让动画更简单。

~~~java
//用这个可以添加多个view自动根据手势进行缩放，例如gif中最上面那两个图。
//只需将GestureView置于需要监听手势的顶层，或者将它作为父布局。
gestureView.attachScaleView(view)
~~~


~~~java
//单独暴露所有OnGestureListener的监听，可自定义长按，拖动，快速浏览等回调
private GestureListener.OnDownListener onDownListener;
private GestureListener.OnShowPressListener onShowPressListener;
private GestureListener.OnSingleTapUpListener onSingleTapUpListener;
private GestureListener.OnScrollListener onScrollListener;
private GestureListener.OnLongPressListener onLongPressListener;
private GestureListener.OnFlingListener onFlingListener;
~~~

