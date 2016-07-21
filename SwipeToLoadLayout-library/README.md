SwipeToLoadLayout 1.0.3

SwipeToLoadLayout，顾名思义，滑动加载布局，可以实现下拉刷新与上拉加载更多。该库支持对ListView，GridView，RecyclerView，WebView，ScrollView等滑动视图的下载刷新与上拉加载更多。

源码见：https://github.com/Aspsine/SwipeToLoadLayout

基本使用
在xml中定义SwipeToLoadLayout：
SwipeToLoadLayout下面定义三个子视图，第一个子代表下拉刷新的头部，第二个代表滑动视图主体，第三个代表上拉加载更多的尾部

<?xml version="1.0" encoding="utf-8"?>
<com.aspsine.swipetoloadlayout.SwipeToLoadLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/swipeToLoadLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#e3e3e3"
    app:default_to_loading_more_scrolling_duration="500"
    app:default_to_refreshing_scrolling_duration="1000"
    app:load_more_complete_delay_duration="0"
    app:load_more_final_drag_offset="@dimen/load_more_final_offset_google"
    app:load_more_trigger_offset="@dimen/load_more_trigger_offset_google"
    app:refresh_complete_delay_duration="0"
    app:refresh_final_drag_offset="@dimen/refresh_final_offset_google"
    app:refresh_trigger_offset="@dimen/refresh_trigger_offset_google"
    app:swipe_style="above" >
    <include
        android:id="@id/swipe_refresh_header"
        layout="@layout/layout_google_header" />
    <ListView
        android:id="@+id/swipe_target"
        style="@style/CommonListView" />
    <include
        android:id="@id/swipe_load_more_footer"
        layout="@layout/layout_google_footer" />
</com.aspsine.swipetoloadlayout.SwipeToLoadLayout>
注意：这三个子视图的id必须是上面所写的，否则会出错


SwipeToLoadLayout常用属性：

app:refresh_enabled：设置是否可以下拉刷新
app:load_more_enabled：设置是否可以上拉加载更多

app:swipe_style：设置下拉刷新与上拉加载的样式，其值为classic，above，blew或scale

app:refresh_trigger_offset：触发下拉刷新的偏移量，默认值是下拉刷新头部的高度
app:load_more_trigger_offset：触发上拉加载更多的偏移量，默认值是上拉加载更多的高度

app:refresh_final_drag_offset：下拉刷新最大可以拖动的偏移量
app:load_more_final_drag_offset：上拉加载更多最大可以拖动的偏移量

app:release_to_refreshing_scrolling_duration：释放下拉刷新持续滚动的时间
app:release_to_loading_more_scrolling_duration：释放上拉加载更多持续滚动的时间

app:refresh_complete_delay_duration：下拉刷新完成延迟的持续时间，就是刷新完成后会有一个延迟
app:load_more_complete_delay_duration：上拉加载更多完成延迟的持续时间

app:refresh_complete_to_default_scrolling_duration：默认完成下拉刷新持续滚动时间，刷新完成后的一个回退滚动时候的时间
app:load_more_complete_to_default_scrolling_duration： 默认完成上拉加载更多持续滚动时间

app:default_to_refreshing_scrolling_duration：默认下拉刷新滚动时间
app:default_to_loading_more_scrolling_duration：默认上拉加载更多滚动时间







