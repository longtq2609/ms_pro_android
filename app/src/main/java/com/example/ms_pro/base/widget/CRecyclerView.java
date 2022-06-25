package com.example.ms_pro.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.example.ms_pro.R;
import com.example.ms_pro.base.adapter.BaveAnimAdapter;

import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;


/**
 * Created by manhi on 2/6/2016.
 */

public class CRecyclerView extends RecyclerView {


    public CRecyclerView(Context context) {
        super(context);
        init();
    }

    public CRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        this.setLayoutManager(layoutManager);
        this.setHasFixedSize(true);
        this.setItemAnimator(new LandingAnimator());
    }

    public void setDefaultAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        AnimationAdapter alphaInAnimationAdapter = new BaveAnimAdapter(adapter);
        alphaInAnimationAdapter.setFirstOnly(false);
        alphaInAnimationAdapter.setDuration(180);
        alphaInAnimationAdapter.setInterpolator(new AccelerateDecelerateInterpolator());
        super.setAdapter(alphaInAnimationAdapter);
    }

    public void setAdapter(AnimationAdapter animationAdapter) {
        super.setAdapter(animationAdapter);
    }

    public void setDivider() {
        this.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST));
    }

    public void setDivider(int color) {
        this.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST, color));
    }


    private Boolean isReadyForPullEnd() {
        try {
            View lastView = this.getChildAt(this.getChildCount() - 1);
            int lastPosition = this.getChildAdapterPosition(lastView);
            if (lastPosition >= this.getAdapter().getItemCount() - 1) {
                return this.getChildAt(this.getChildCount() - 1).getBottom() <=
                        this.getBottom();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void loadMore(final LoadMoreListener loadMoreListener) {
        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (CRecyclerView.this.isReadyForPullEnd()) {
                    loadMoreListener.onScrolled();
                }
            }
        });
    }


    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        if (focused instanceof RecyclerView)
            return;
        super.requestChildFocus(child, focused);
    }

    public interface LoadMoreListener {
        void onScrolled();
    }

    public interface OnMeasuredViewSize {
        void getHeight(int height);

        void getWidth(int height);
    }

    public interface RecyclerViewReadyCallback {
        void onLayoutReady();
    }

    static class DividerItemDecoration extends ItemDecoration {

        private static final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };

        static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

        static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

        private Drawable mDivider;

        private int mOrientation;

        private Context context;
        private Paint paint;

        DividerItemDecoration(Context context, int orientation) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            this.context = context;
            paint = new Paint();
            paint.setColor(ContextCompat.getColor(context, R.color.line));
            paint.setStrokeWidth(SizeUtils.dp2px(1));
            mDivider = a.getDrawable(0);
            a.recycle();
            setOrientation(orientation);
        }

        DividerItemDecoration(Context context, int orientation, int color) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            this.context = context;
            paint = new Paint();
            paint.setColor(ContextCompat.getColor(context, color));
            paint.setStrokeWidth(SizeUtils.dp2px(1));
            mDivider = a.getDrawable(0);
            a.recycle();
            setOrientation(orientation);
        }

        DividerItemDecoration(int orientation, Drawable divider) {
            mDivider = divider;
            setOrientation(orientation);
        }

        public void setOrientation(int orientation) {
            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
                throw new IllegalArgumentException("invalid orientation");
            }
            mOrientation = orientation;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent) {
            if (mOrientation == VERTICAL_LIST) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }

        private void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                final LayoutParams params = (LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
//                mDivider.setBounds(left, top, right, bottom);
////                mDivider.setColorFilter(new PorterDuffColorFilter(
////                        ContextCompat.getColor(context, R.color.line), PorterDuff.Mode.MULTIPLY));
//                mDivider.draw(c);
                c.drawRect(new Rect(left, top, right, bottom), paint);
            }
        }

        private void drawHorizontal(Canvas c, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                final LayoutParams params = (LayoutParams) child
                        .getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicHeight();
//                mDivider.setBounds(left, top, right, bottom);
////                mDivider.setColorFilter(new PorterDuffColorFilter(
////                        ContextCompat.getColor(context, R.color.line), PorterDuff.Mode.MULTIPLY));
//                mDivider.draw(c);
                c.drawRect(new Rect(left, top, right, bottom), paint);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }
    }

    public class DividerFromDrawableDecoration extends ItemDecoration {

        private Drawable mDivider;

        public DividerFromDrawableDecoration(Drawable divider) {
            mDivider = divider;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            super.getItemOffsets(outRect, view, parent, state);

            if (parent.getChildAdapterPosition(view) == 0) {
                return;
            }
            outRect.top = mDivider.getIntrinsicHeight();
        }

        @Override
        public void onDraw(Canvas canvas, RecyclerView parent, State state) {
            int dividerLeft = parent.getPaddingLeft();
            int dividerRight = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                View child = parent.getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();

                int dividerTop = child.getBottom() + params.bottomMargin;
                int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

                mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                mDivider.draw(canvas);
            }
        }
    }
}
