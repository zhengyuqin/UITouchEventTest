*fragment生命周期会经历以下几个状态： INITIALIZING //not yet created CREATED //created ACTIVITY_CREATED //the activity has finished its creation STOPPED //created ,but not started STARTED //created and started ,but not resumed RESUMED //created ,started and resumed

*isInBackStack():是否在fragmentManager的后退栈中 *setRetainInstance(boolean retain):如果retain=true&&mParentFragment!=null,是不能设置这个 *isFromLayout():true show that this fragment inflated from layout *isInLayout():

onCreate(@Nullable Bundle savedInstanceState) 注释：这个方法在activity的onCreate的进行也同样进行，所以，我们不能依赖于activity的正在初始化的内容视图， 我们可以在onActivityCreated()这个方法进行处理

onViewCreated() 注释：这个方法在onCreateView()之后立即执行，在onViewRestoreState()之前， The fragment's view hierarchy is not however attached to its parent at this point. 这句话意思说这个fragment的视图层次还没有添加到他的parent上，这个就好像我们在写adapter.getView(), inflate()我们常用false。

onActivityCreated() 注释：在activity.created成功后，会回调这个方法，我们有时会调用fragment.setRetainInstance()这个方法么， 这个方法就是保存了fragment的事例，即使这个fragment detachFromActivity也是存在的，那么只有在这个 方法回调后，表示这个activity已经created了，我们就可以恢复之前的fragment实例。

onViewStateRestored() 注释：举个例子吧,控件checkbutton,我们希望重新进来界面时候，保持之前的状态，那我们可以在这个地方恢复button状态

isAdded() 注释：表示这个fragment是否添加到activity

isDetached() 注释：表示这个fragment是否脱离了UI

isRemoving() 注释：表示这个fragment是否正在从activity脱离

isResumed() 注释：若该fragment 在onResume()和onPause()之间就是true

FragmentManager:

FragmentTransaction beginTransaction() 注释：fragmentTransaction的创建和提交只能在activity保存状态之前，因为application希望保存fragment的状态。

executePendingTransactions 注释：fragmentTransaction本身是在在主线程异步执行的，若想立即执行，我们可以调用这个方法马上执行。

putFragment(Bundle bundle,String key,Fragment fragment) 注释：我们可以把fragment的引用放到bundle中，这样我们就保存了这个fragment的状态，我们可以通过getFragment取出来。

saveFragmentInstanceState(Fragment fragment) 注释：保存fragment的状态，我们可以在create新的fragment时候恢复本身的状态。 限制条件：(1)这个fragment必须attach to fragmentManager (2)新的fragment跟这个fragment必须是相同 className; (3)fragment这个保存的状态不能包括其他fragment的引用，还有fragment'target,requestCode

isDestroyed() 注释：当Activity.onDestory()调用时候，fragmentManager这时候已经死掉了

execPendingActions():注释:马上提交等待的事务,一定要执行在主线程.原理,原先有个挂起的队列,先停掉上次execPendingActions(),然后执行每个Runnable.同时,如果有fragment延迟加载start状态,等事务不在是忙碌时候,就可以加载fragment的start方法.

setBackStackIndex(int index,backStackRecord bse):注释:设置在后退栈中某个地方的进出栈记录.

freeBackStackIndex(int index)注释:只是从后退栈队列中把记录剔除

popBackStackState(Handle handle,String name, int id, int flags)
注释:这个方法很重要,handle = mActivity.mHandler,name 默认为null,id 默认为-1,flags =0/1(POP_BACK_STACK_INCLUSIVE)
    分以下三种情况分析: name = null && id < 0 ,flags = 0,这时候只会删除最上层的fragment
                     name = null || id < 0 ,flags = 1,这时候会删掉全部的fragment,
                     name != null || id > 0 ,flags = 0,这时候只会把第一个找到的放到fragmentPop队列中,
                                             假设这时候找到的fragment在index = 3,total = 5,这时候会弹出3,4,5.
                                             flags = 1,这时候会把接下来的连续framgent也添加进来,
                                             假设index = 2,3 ,就会弹出:2,3,4,5
                                             若index = 1,3,那还是弹出3,4,5,因为2找不到时候已经退出循环了.

saveAllState()
注释:保存所有状态,包括添加了多少,后退栈中active fragment的状态保存,还有每个fragment的操作记录.

moveToState(Fragment f, int newState, int transit, int transitionStyle,boolean keepActive)
注释:对fragment状态的切换,首先我们知道fragment有几种状态:
          INITIALIZING;
          CREATED;
          ACTIVITY_CREATED;
          STOPPED;
          STARTED;
          RESUME;
   1.(!f.mAdded || f.mDetched)&&(newState > Fragment.CREATED) newState = Fragment.CREATED;
   2.(f.mRemoving && newState > f.state) newState = f.mState;
   3.(f.mDeferStart && f.mState < Fragment.STARTED && newState > Fragment.STOPPED) newState = Fragment.STOPPED;
   4.if(f.mState < newState) 解析来看源码好了,不做记录了.

POP_BACK_STACK_INCLUSIVE 注释：表示直到找到我们想要的标志时候，不然清掉整个backStack;

Loader几种生命状态： boolean started; boolean abandoned; boolean reset; boolean contentChanged; boolean processingChange;


思考activity是怎么帮fragment 保存状态的:

看看activity.onSaveInstanceState做了什么:
protected void onSaveInstanceState(Bundle outState) {
        outState.putBundle(WINDOW_HIERARCHY_TAG, mWindow.saveHierarchyState());
        Parcelable p = mFragments.saveAllState();
        if (p != null) {
            outState.putParcelable(FRAGMENTS_TAG, p);
        }
        getApplication().dispatchActivitySaveInstanceState(this, outState);
    }
注意:mWindow.saveHierarchyState(),mFragment.saveAllState();

同理我们可以去了解activity.onRestoreInstanceState();

先看以下三个方法:
saveFragmentViewState():
保存fragment的view状态的,举个例子:
假设我们有个ViewPager,里面有4个fragment,每个fragment都有listView,为什么我们可以在viewPager切换fragment时候,listView还保持当时的位置呢
     就是android提供了View的保存状态数,可以从这里恢复,那保存和恢复的源码在哪呢,肯定就是activity的onSaveInstanceState()和
     onRestoreInstanceState()

看mWindow.saveHierarchyState()源码就知道怎么保存view状态树的
简单总结一下:activity在状态需要保存时候,直接new了一个SpareArray,建立mViewId 和Parcelable的映射,其中每一个view都会使用一个Parcelable来序列化
           需要保存下来的内容,ViewGroup在保存自己的同时,也会调用子类的保存状态的方法,并把上下传下来的SpareArray一直传下去


先看这个方法在FragmentManager哪处调用先:
1.切换Fragment 状态到ACTIVITY_CREATED:就是activity创建成功后会通知fragment
2.saveFragmentBasicState() <-- saveFragmentInstanceState()(这个在FragmentStatePagerAdapter.destroyItem调用)
(saveFragmentBasicState():注释:不仅保存fragment我们要写的onSaveInstanceState()还有view状态.)


建议看看FragmentManager.saveAllState()和restoreAllStore()是怎样在activity在特殊情况下恢复fragment;

惊讶的发现:原来FragmentManager.saveAllState()在Activity的onSaveInstance()调用
         而FragmentManager.restoreAllStore()是在Activity的onCreate()调用的,而且你在fragment不能看到onRestoreInstance()的类似方法.

 protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (DEBUG_LIFECYCLE) Slog.v(TAG, "onCreate " + this + ": " + savedInstanceState);
        if (mLastNonConfigurationInstances != null) {
            mAllLoaderManagers = mLastNonConfigurationInstances.loaders;
        }
        if (mActivityInfo.parentActivityName != null) {
            if (mActionBar == null) {
                mEnableDefaultActionBarUp = true;
            } else {
                mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
            }
        }
        if (savedInstanceState != null) {
            Parcelable p = savedInstanceState.getParcelable(FRAGMENTS_TAG);
            mFragments.restoreAllState(p, mLastNonConfigurationInstances != null
                    ? mLastNonConfigurationInstances.fragments : null);
        }
        mFragments.dispatchCreate();//是在onCreate调用的,可以结合官网的生命周期来看
        getApplication().dispatchActivityCreated(this, savedInstanceState);
        if (mVoiceInteractor != null) {
            mVoiceInteractor.attachActivity(this);
        }
        mCalled = true;
    }