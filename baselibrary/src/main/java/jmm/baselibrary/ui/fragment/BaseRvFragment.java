package jmm.baselibrary.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.kennyc.view.MultiStateView;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import jmm.baselibrary.R;
import jmm.baselibrary.R2;
import jmm.baselibrary.bean.LoadStatus;
import jmm.baselibrary.rx.BaseSubscriber;
import jmm.baselibrary.ui.adapter.BaseRvAdapter;
import rx.Observable;

/**
 * user:Administrator
 * time:2018 05 07 10:34
 * package_name:jmm.baselibrary.ui.fragment
 */
public abstract class BaseRvFragment<T> extends BaseFragment {

    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R2.id.ptrFrameLayout)
    PtrClassicFrameLayout mPtrFrameLayout;
    @BindView(R2.id.multiStateView)
    MultiStateView mMultiStateView;

    BaseRvAdapter<T> mRvAdapter;

    protected int mPageCount;
    protected int mCurrentPage = 1;
    protected static int PAGE_SIZE = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_rv;
    }

    @Override
    protected void initView() {
        initRv();
        initPtrFrameLayout();
        initMultiStateView();
    }

    private void initMultiStateView() {
        mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR).findViewById(R.id.msv_ll_error).setOnClickListener(v -> loadData(LoadStatus.LOADING));
    }

    /**
     * 初始化RecyclerView
     */
    private void initRv() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener());
        mRvAdapter = getRecyclerViewAdapter();
        if (getItemDecoration() != null) {
            mRecyclerView.addItemDecoration(getItemDecoration());
        }
        mRecyclerView.setAdapter(mRvAdapter);
        mRvAdapter.setEnableLoadMore(true); //开启加载更多
//        mRvAdapter.setLoadMoreView(new CustomLoadMoreView());     //自定义加载更多
        mRvAdapter.setOnLoadMoreListener(() -> loadData(LoadStatus.MORE), mRecyclerView);//添加加载更多监听
        mRvAdapter.setEmptyView(R.layout.layout_state_empty);
    }

    /**
     * 初始化下拉刷新控件
     */
    private void initPtrFrameLayout() {
        mPtrFrameLayout.setLastUpdateTimeRelateObject(this);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadData(LoadStatus.REFRESH);
            }
        });
    }

    /**
     * 加载数据
     */
    protected void loadData(LoadStatus status) {
        if (status == LoadStatus.MORE) {
            String currentPage = getCurrentPage();
            if (currentPage != null) {
                mPageCount = Integer.parseInt(currentPage);
            }
            mCurrentPage = ++mCurrentPage;
            if (mCurrentPage > mPageCount) {
                mRvAdapter.loadMoreEnd();
                return;
            }
        } else {
            mCurrentPage = 1;
        }
        getApi(String.valueOf(mCurrentPage)).subscribe(new BaseSubscriber<List<T>>() {

            @Override
            public void onStart() {
                super.onStart();
                if (status == LoadStatus.LOADING) {
                    mRecyclerView.scrollToPosition(0);
                    mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
                }
            }

            @Override
            public void onNext(List<T> t) {
                switch (status) {
                    case LOADING:
                    case REFRESH:
                        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                        if (t.size() < PAGE_SIZE) {
                            mRvAdapter.loadMoreEnd();
                        }
                        mRvAdapter.setNewData(t); // 刷新或者第一次加载成功
                        mPtrFrameLayout.refreshComplete();//刷新完成
                        if (!hasLoadMore()) {
                            mRvAdapter.loadMoreEnd();
                        }
                        break;
                    case MORE:
                        mRvAdapter.addData(t);
                        if (mCurrentPage >= mPageCount) {
                            mRvAdapter.loadMoreEnd(); //没有更多数据了
                        }
                        mRvAdapter.loadMoreComplete(); // 加载更多完成
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                switch (status) {
                    case LOADING:
                        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
                        break;
                    case REFRESH:
                        mPtrFrameLayout.refreshComplete();//刷新完成
                        break;
                    case MORE:
                        mRvAdapter.loadMoreFail(); // 加载更多失败
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void lazyFetchData() {
        loadData(LoadStatus.LOADING);
    }

    public boolean hasLoadMore() {
        return true;
    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }


    protected abstract String getCurrentPage();

    protected abstract Observable<List<T>> getApi(String currNum);

    protected abstract BaseRvAdapter<T> getRecyclerViewAdapter();

    protected abstract void onItemClick(BaseQuickAdapter adapter, View view, int position);

    protected void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    protected void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    protected void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    private class RecyclerItemClickListener extends SimpleClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            BaseRvFragment.this.onItemClick(adapter, view, position);
        }

        @Override
        public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
            BaseRvFragment.this.onItemLongClick(adapter, view, position);
        }

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            BaseRvFragment.this.onItemChildClick(adapter, view, position);
        }

        @Override
        public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
            BaseRvFragment.this.onItemChildLongClick(adapter, view, position);
        }
    }

}
