Fragment��Ҫ���������
========
*fragment�������ڻᾭ�����¼���״̬��
    INITIALIZING      //not yet created
    CREATED           //created
    ACTIVITY_CREATED  //the activity has finished its creation
    STOPPED           //created ,but not started
    STARTED           //created and started ,but not resumed
    RESUMED           //created ,started and resumed

*isInBackStack():�Ƿ���fragmentManager�ĺ���ջ��
*setRetainInstance(boolean retain):���retain=true&&mParentFragment!=null,�ǲ����������
*isFromLayout():true show that this fragment inflated from layout
*isInLayout():


onCreate(@Nullable Bundle savedInstanceState)
ע�ͣ����������activity��onCreate�Ľ���Ҳͬ�����У����ԣ����ǲ���������activity�����ڳ�ʼ����������ͼ��
     ���ǿ�����onActivityCreated()����������д���

onViewCreated()
ע�ͣ����������onCreateView()֮������ִ�У���onViewRestoreState()֮ǰ��
     The fragment's view hierarchy is not however attached to its parent at this point.
     ��仰��˼˵���fragment����ͼ��λ�û����ӵ�����parent�ϣ�����ͺ���������дadapter.getView(),
     inflate()���ǳ���false��

onActivityCreated()
ע�ͣ���activity.created�ɹ��󣬻�ص����������������ʱ�����fragment.setRetainInstance()�������ô��
     ����������Ǳ�����fragment����������ʹ���fragment detachFromActivityҲ�Ǵ��ڵģ���ôֻ�������
     �����ص��󣬱�ʾ���activity�Ѿ�created�ˣ����ǾͿ��Իָ�֮ǰ��fragmentʵ����

onViewStateRestored()
ע�ͣ��ٸ����Ӱ�,�ؼ�checkbutton,����ϣ�����½�������ʱ�򣬱���֮ǰ��״̬�������ǿ���������ط��ָ�button״̬


isAdded()
ע�ͣ���ʾ���fragment�Ƿ���ӵ�activity

isDetached()
ע�ͣ���ʾ���fragment�Ƿ�������UI

isRemoving()
ע�ͣ���ʾ���fragment�Ƿ����ڴ�activity����

isResumed()
ע�ͣ�����fragment ��onResume()��onPause()֮�����true


FragmentManager:
FragmentTransaction beginTransaction()
ע�ͣ�fragmentTransaction�Ĵ������ύֻ����activity����״̬֮ǰ����Ϊapplicationϣ������fragment��״̬��

executePendingTransactions
ע�ͣ�fragmentTransaction�������������߳��첽ִ�еģ���������ִ�У����ǿ��Ե��������������ִ�С�

putFragment(Bundle bundle,String key,Fragment fragment)
ע�ͣ����ǿ��԰�fragment�����÷ŵ�bundle�У��������Ǿͱ��������fragment��״̬�����ǿ���ͨ��getFragmentȡ������

saveFragmentInstanceState(Fragment fragment)
ע�ͣ�����fragment��״̬�����ǿ�����create�µ�fragmentʱ��ָ������״̬��
     ����������(1)���fragment����attach to fragmentManager
             (2)�µ�fragment�����fragment��������ͬ className;
              (3)fragment��������״̬���ܰ�������fragment�����ã�����fragment'target,requestCode

isDestroyed()
ע�ͣ���Activity.onDestory()����ʱ��fragmentManager��ʱ���Ѿ�������



POP_BACK_STACK_INCLUSIVE
ע�ͣ���ʾֱ���ҵ�������Ҫ�ı�־ʱ�򣬲�Ȼ�������backStack;

Loader��������״̬��
boolean started;
boolean abandoned;
boolean reset;
boolean contentChanged;
boolean processingChange;


