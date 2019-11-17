package cn.fjmz.agt.presenter.compl;

import cn.fjmz.agt.myinterface.INoCommitItem;
import cn.fjmz.agt.presenter.ICommitPresenter;

import java.util.List;
import java.util.Set;

/**
 *
 * Created by Administrator on 2016/5/9.
 */
public class CommitPresenterImpl<T extends INoCommitItem> implements ICommitPresenter<T> {
    @Override
    public void upload(List<T> info, Set<Integer> keySet) {

    }

    @Override
    public void cancel(List<T> commitData) {

    }

//    private View mView;
//    private CompositeSubscription mCommitSubscription;
//
//    public CommitPresenterImpl(ICommitPresenter.View view) {
//        mView = view;
//        mCommitSubscription = new CompositeSubscription();
//    }
//
//    @Override
//    public void upload(List<T> infos, Set<Integer> keySet) {
//        if (keySet == null || keySet.isEmpty()) {
//            return;
//        }
//        for (Integer i : keySet) {
//            T info = infos.get(i);
//            final int finalI = i;
//            final T finalInfo = info;
//            Subscription subscription = Observable.create(new Observable.OnSubscribe<String>() {
//                @Override
//                public void call(Subscriber<? super String> pSubscriber) {
//                    try {
//                        ArrayList<HashMap<String, Object>> data = finalInfo.upload();
//                        if (data == null || data.isEmpty()) {
//                            pSubscriber.onNext("");
//                        } else {
//                            pSubscriber.onNext(StringUtil.noNull(data.get(0).get("retstr")));
//                        }
//                    } catch (Exception pE) {
//                        pE.printStackTrace();
//                        pSubscriber.onError(pE);
//                    }
//                }
//            }).subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Subscriber<String>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            mView.commitStatus(finalI, false);
//                        }
//
//                        @Override
//                        public void onNext(String pS) {
//                            mView.commitStatus(finalI, TextUtils.isEmpty(pS));
//                            if (!TextUtils.isEmpty(pS)) {
//                                mView.toast(pS);
//                            }
//                        }
//                    });
//            mCommitSubscription.add(subscription);
//        }
//    }
//
//    @Override
//    public void cancel(List<T> commitData) {
//        for (T data : commitData) {
//            if (data.isCommitSuccess()) {
//                data.deleteCommit(mView.getContext());
//            }
//        }
//    }
}
