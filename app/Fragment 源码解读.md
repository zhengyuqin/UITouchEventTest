Fragment重要方法解读：
========
*fragment生命周期会经历以下几个状态：
    INITIALIZING      //not yet created
    CREATED           //created
    ACTIVITY_CREATED  //the activity has finished its creation
    STOPPED           //created ,but not started
    STARTED           //created and started ,but not resumed
    RESUMED           //created ,started and resumed

*isInBackStack():是否在fragmentManager的后退栈中
*setRetainInstance(boolean retain):如果retain=true&&mParentFragment!=null,是不能设置这个
*isFromLayout():true show that this fragment inflated from layout
*isInLayout():


onCreate(@Nullable Bundle savedInstanceState)
注释：这个方法在activity的onCreate的进行也同样进行，所以，我们不能依赖于activity的正在初始化的内容视图，
     我们可以在onActivityCreated()这个方法进行处理

onViewCreated()
注释：这个方法在onCreateView()之后立即执行，在onViewRestoreState()之前，
     The fragment's view hierarchy is not however attached to its parent at this point.
     这句话意思说这个fragment的视图层次还没有添加到他的parent上，这个就好像我们在写adapter.getView(),
     inflate()我们常用false。

onActivityCreated()
注释：在activity.created成功后，会回调这个方法，我们有时会调用fragment.setRetainInstance()这个方法么，
     这个方法就是保存了fragment的事例，即使这个fragment detachFromActivity也是存在的，那么只有在这个
     方法回调后，表示这个activity已经created了，我们就可以恢复之前的fragment实例。

onViewStateRestored()
注释：举个例子吧,控件checkbutton,我们希望重新进来界面时候，保持之前的状态，那我们可以在这个地方恢复button状态


isAdded()
注释：表示这个fragment是否添加到activity

isDetached()
注释：表示这个fragment是否脱离了UI

isRemoving()
注释：表示这个fragment是否正在从activity脱离

isResumed()
注释：若该fragment 在onResume()和onPause()之间就是true


FragmentManager:
FragmentTransaction beginTransaction()
注释：fragmentTransaction的创建和提交只能在activity保存状态之前，因为application希望保存fragment的状态。

executePendingTransactions
注释：fragmentTransaction本身是在在主线程异步执行的，若想立即执行，我们可以调用这个方法马上执行。

putFragment(Bundle bundle,String key,Fragment fragment)
注释：我们可以把fragment的引用放到bundle中，这样我们就保存了这个fragment的状态，我们可以通过getFragment取出来。

saveFragmentInstanceState(Fragment fragment)
注释：保存fragment的状态，我们可以在create新的fragment时候恢复本身的状态。
     限制条件：(1)这个fragment必须attach to fragmentManager
             (2)新的fragment跟这个fragment必须是相同 className;
              (3)fragment这个保存的状态不能包括其他fragment的引用，还有fragment'target,requestCode

isDestroyed()
注释：当Activity.onDestory()调用时候，fragmentManager这时候已经死掉了



POP_BACK_STACK_INCLUSIVE
注释：表示直到找到我们想要的标志时候，不然清掉整个backStack;

Loader几种生命状态：
boolean started;
boolean abandoned;
boolean reset;
boolean contentChanged;
boolean processingChange;


