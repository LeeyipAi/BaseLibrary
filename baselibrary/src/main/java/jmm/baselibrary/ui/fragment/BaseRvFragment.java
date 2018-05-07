package jmm.baselibrary.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.kennyc.view.MultiStateView;

import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
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
public abstract class BaseRvFragment<T> extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R2.id.bgaRefreshLayout)
    BGARefreshLayout mBgaRefreshLayout;
    @BindView(R2.id.multiStateView)
    MultiStateView mMultiStateView;

    BaseRvAdapter<T> mRvAdapter;

    protected int mPageCount;
    protected int mCurrentPage = 1;

    protected static int PAGE_SIZE = 10;
    private BGANormalRefreshViewHolder mRefreshViewHolder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_rv;
    }

    @Override
    protected void initView() {
        initRv();
        initBgaRefreshLayout();
        loadData(LoadStatus.LOADING);
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
    }

    /**
     * 初始化下拉刷新控件
     */
    private void initBgaRefreshLayout() {
        mBgaRefreshLayout.setDelegate(this);
        mRefreshViewHolder = new BGANormalRefreshViewHolder(getActivity(), true);
        mRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg);
        mRefreshViewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg);
        mBgaRefreshLayout.setRefreshViewHolder(mRefreshViewHolder);
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
        } else {
            mCurrentPage = 1;
        }
        getApi(String.valueOf(mCurrentPage)).subscribe(new BaseSubscriber<List<T>>() {

            @Override
            public void onStart() {
                super.onStart();
                if (status == LoadStatus.LOADING) {
                    mRecyclerView.scrollToPosition(0);
//                    vml.showLoadingView();
                }
            }

            @Override
            public void onNext(List<T> t) {
                if (status == LoadStatus.MORE) {
                    if (mCurrentPage >= mPageCount) {
                        mRvAdapter.loadMoreEnd(); //没有更多数据了
                    }
                    mRvAdapter.addData(t);
                } else {
//                    vml.showContentView();
                    mRvAdapter.setNewData(t); // 刷新或者第一次加载成功
//                    }
                    if (t.size() < PAGE_SIZE) {
                        mRvAdapter.loadMoreEnd();
                    }
//                    if (!hasLoadMore()) {
//                        mRvAdapter.loadMoreEnd();
//                    }
                }
                switch (status) {
                    case REFRESH:
//                        ptrFrameLayout.refreshComplete();//刷新完成
                        break;
                    case MORE:
//                        mRvAdapter.loadMoreComplete(); // 加载更多完成
                        break;
                    default:
                        break;
                }
                mBgaRefreshLayout.endRefreshing();
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                switch (status) {
                    case LOADING:
//                        vml.showErrorView();//失败重试
                        break;
                    case REFRESH:
//                        ptrFrameLayout.refreshComplete(); //下拉刷新完成
                        break;
                    case MORE:
//                        mAdapter.loadMoreFail(); // 加载更多失败
                        break;
                    default:
                        break;
                }
            }
        });
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        loadData(LoadStatus.REFRESH);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (mCurrentPage < 3) {
            loadData(LoadStatus.MORE);
            return true;
        }else {
            return false;
        }
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
