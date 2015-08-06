ViewGroup.dispatchTouchEvent():
注释:这个比较复杂,首先决定是否要拦截这个事件onInterceptTouchEvent(),分三种情况:
1.也是系统默认的方式,都是false,
这时候,就要看子view的onTouchEvent()返回boolean值了,
    (0)若是都是返回true,View/ViewGroup/Activity.dispatchTouchEvent = true --> view.onClick()
    (1)若是ACTION_DOWN 返回false, view.dispatchTouchEvent = false,后续的事件都交给了ViewGroup
            这时候,ViewGroup就是ACTION_DOWN: onTouch --> onTouchEvent --> dispatchTouchEvent
                               ACTION_MOVE,ACTION_UP 都是这个动作,所以,ViewGroup的onClick会执行(首先要确保ViewGroup是可点击的)
    (2)若是ACTION_MOVE 返回false, view.dispatchTouchEvent = false,ViewGroup.dispatchTouchEvent = false,事件传给了activity,
            activity --> onTouchEvent = false --> dispatchTouchEvent = false;
            但有一点跟ACTION_DOWN不同的是,接下来的ACTION_UP照样给View 处理, View/ViewGroup/activity.dispatchTouchEvent = true,view --> onClick()会执行
    (3)若是ACTION_UP 返回false, view.dispatchTouchEvent = false, ViewGroup.dispatchTouchEvent = false,这样View,ViewGroup的点击事件都不会产生.
            事件传给了activity,activity --> dispatchTouchEvent = false

    总结: 当是系统默认情况下,ViewGroup.onInterceptTouchEvent 在每个阶段都会执行

2.当ACTION_DOWN 返回true,事件都是没法传到View.
    (0)若是子View 都返回true,事件没有经过View,所以
            ViewGroup.onTouch --> onTouchEvent --> dispatchTouchEvent --> onClick

    总结,一旦onInterceptTouchEvent 在ACTION_DOWN返回true,ViewGroup.onInterceptTouchEvent 之后不会调用,并且View不会接收到后续的事件

3.当ACTION_MOVE 返回true,
    (0)若是子View 都返回true,处理流程如下:
            ACTION_DOWN:View.dispatchTouchEvent(ACTION_DOWN) = true,
            ACTION_MOVE:View.dispatchTouchEvent(ACTION_CANCEL) = true;ViewGroup.dispatchTouchEvent = true,后续事件不会传到View上了.
            ACTION_UP:ViewGroup.onTouch --> onTouchEvent --> dispatchTouchEvent = true 但是mLayout.onclick 没有调用
            疑点:为什么不会调用:这是View.onTouchEvent.ACTION_UP的源码:

                   if (((viewFlags & CLICKABLE) == CLICKABLE ||
                                  (viewFlags & LONG_CLICKABLE) == LONG_CLICKABLE)) {
                              switch (event.getAction()) {
                                  case MotionEvent.ACTION_UP:
                                      boolean prepressed = (mPrivateFlags & PFLAG_PREPRESSED) != 0;
                                      if ((mPrivateFlags & PFLAG_PRESSED) != 0 || prepressed) {
                                          // take focus if we don't have it already and we should in
                                          // touch mode.
                                          boolean focusTaken = false;
                                          if (isFocusable() && isFocusableInTouchMode() && !isFocused()) {
                                              focusTaken = requestFocus();
                                          }

                                          if (prepressed) {
                                              // The button is being released before we actually
                                              // showed it as pressed.  Make it show the pressed
                                              // state now (before scheduling the click) to ensure
                                              // the user sees it.
                                              setPressed(true, x, y);
                                         }

                                          if (!mHasPerformedLongPress) {
                                              // This is a tap, so remove the longpress check
                                              removeLongPressCallback();

                                              // Only perform take click actions if we were in the pressed state
                                              if (!focusTaken) {
                                                  // Use a Runnable and post this rather than calling
                                                  // performClick directly. This lets other visual state
                                                  // of the view update before click actions start.
                                                  if (mPerformClick == null) {
                                                      mPerformClick = new PerformClick();
                                                  }
                                                  if (!post(mPerformClick)) {
                                                      performClick();
                                                  }
                                              }
                                          }

                                          if (mUnsetPressedState == null) {
                                              mUnsetPressedState = new UnsetPressedState();
                                          }

                                          if (prepressed) {
                                              postDelayed(mUnsetPressedState,
                                                      ViewConfiguration.getPressedStateDuration());
                                          } else if (!post(mUnsetPressedState)) {
                                              // If the post failed, unpress right now
                                              mUnsetPressedState.run();
                                          }

                                          removeTapCallback();
                                      }
                                      break;

    (1)若是ACTION_DOWN返回false,View.onTouchEvent = false --> dispatchTouchEvent = false,这时候全部的事件全部转移动到ViewGroup上了
           ViewGroup.onTouch --> onTouchEvent --> dispatchTouchEvent = true --> onclick
    (2)若是ACTION_MOVE返回false,情况跟(0)是一样的,ViewGroup.onClick 不执行
    (3)若是ACTION_UP返回false,情况如上
    (4)若是ACTION_CANCEL返回false,在ACTION_MOVE,View/ViewGroup.dispatchTouchEvent = false,事件给了activity
                                 在ACTION_UP,事件给了ViewGroup处理.
    总结:在ACTION_MOVE 进行拦截,若是View在ACTION_DOWN就表示不处理了,那么这时候拦截是没有用的,因为事件直接给了ViewGroup了,
                              若是View在ACTION_MOVE就表示不处理了,那么这时候会接收到ACTION_CANCEL事件,ACTION_UP就把事件给了ViewGroup,但没有ViewGroup.onClick()

4.在ACTION_UP 返回true;
    (0)(1) 默认情况跟当ACTION_DOWN为false,只有在ACTION_UP,View会接收到ACTION_CANCEL,这样view.dispatchTouchEvent = true,但肯定没有View.onClick();
    (2)当ACTION_MOVE 返回false,View.ViewGroup.dispatchTouchEvent 为false,事件ACTION_MOVE直接给activity,
          activity --> onTouch --> onTouchEvent --> dispatchTouchEvent
          但后面的操作会有一个ACTION_CANCEL 给到View.这时候view.dispatchTouchEvent = true
    (3)当ACTION_UP 返回false,同(0)(1),都是被View.ACTION_CANCEL处理了.


requestDisallowInterceptTouchEvent(boolean ):
注释:这个方法有效的地方肯定是onInterceptTouchEvent.ACTION_DOWN之后的事件才有效.如果该ViewGroup.onInterceptTouchEven在ACTION_DOWN时候return true,
在View添加这个方法是没用的.





声明: dispatchTouchEvent = dte ;
      onTouchEvent = ote;
      onTouch = ot;
      onInterceptTouchEvent = oite;
总结: ACTION_DOWN : 如果child dte = false,就会把后续事件(包括ACTION_DOWN)扔给parent处理,child 不处理ACTION_MOVE,ACTION_UP,ACTION_CANCEL逻辑
                   如果拦截了,就完全是parent处理了
      ACTION_MOVE : 假设没有被拦截:
                               1.child dte = false,parent 是不处理(ACTION_MOVE)事件,直接交给activity处理.但是ACTION_UP还是交给child 处理.
                               2.child dte = true, 被child处理.
                    假设被拦截:  oite.ACTION_MOVE = true:
                                     不管dte.ACTION_MOVE = true/false,都会接收到ACTION_CANCEL,这就要取决了ACTION_CANCEL的result,
                                          若 ACTION_CANCEL = true(默认) child/parent.dte = true,ACTION_MOVE 事件给activity处理.
                                          若 ACTION_CANCEL = false(不建议这么做) child/parent.dte = false, 也是交给activity处理.
                                          后续的动作ACTION_UP 肯定交给parent ,但是parent 不做onClick 处理.
      ACTION_UP:    假设没有被拦截:
                               1.child dte = false,child/parent.dte = false,交给activity处理
                               2.child dte = true,交个child 处理.
                    假设被拦截: 不管dte.ACTION_UP = false/true,child要处理是ACTION_CANCEL事件,就要取决ACTION_CANCEL的result.



看ScrollView.onTouchEvent():
发现在ACTION_DOWN,源码调用requestDisallowInterceptTouchEvent(true),出现ScrollView的事件被吃掉了,很可能是parent.oite.ACTION_DOWN = false;