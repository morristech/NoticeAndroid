package lin.jiang.notice.presentation.componet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lin.jiang.notice.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * @author YangLinjiang
 * @date 2015-10-30上午9:15:06
 * @description ViewPager Indicator
 */
public class ViewPagerIndicator extends LinearLayout implements OnPageChangeListener {
	/**指示器默认高度比*/
	private float indicatorH = 0.05f;
	/**指示器默认宽度比（每个tab）*/
	private float indicatorW = 0.75f;
	/**指示器默认颜色*/
	private int indicatorColor = Color.BLUE;
	/**Tab文字默认颜色*/
	private int indicatorTextColor = Color.BLACK;
	/**Tab文字默认选中颜色*/
	private int indicatorTextChosenColor = Color.GREEN;
	/**Tab文字默认大小*/
	private int indicatorTextSize = 16;

	private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	/**Tab文字集合*/
	private List<TextView> mTitles = new ArrayList<TextView>();
	private ViewPager mViewPager;
	private OnPageChangeListener mListener;

	private int mScrollState;
	private int mCurrentPage;
	private float mPositionOffset;

	public List<TextView> getmTitles() {
		return mTitles;
	}
	public void setInDicatorH(float indicatorH) {
		this.indicatorH = indicatorH;
		invalidate();
	}
	public void setInDicatorW(float indicatorW) {
		this.indicatorW = indicatorW;
		invalidate();
	}
	public void setIndicatorColor(int indicatorColor) {
		this.indicatorColor = indicatorColor;
		mPaint.setColor(this.indicatorColor);
		invalidate();
	}
	public void setIndicatorTextColor(int indicatorTextColor) {
		this.indicatorTextColor = indicatorTextColor;
	}
	public void setIndicatorTextChosenColor(int indicatorTextChosenColor) {
		this.indicatorTextChosenColor = indicatorTextChosenColor;
	}
	public void setIndicatorTextSize(int indicatorTextSize) {
		this.indicatorTextSize = indicatorTextSize;
	}
	
	public ViewPagerIndicator(Context context) {
		this(context, null);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setWillNotDraw(false);
		setIndicatorColor(context.getResources().getColor(R.color.colorPrimary));
		setIndicatorTextColor(context.getResources().getColor(R.color._tab_nor));
		setIndicatorTextChosenColor(context.getResources().getColor(R.color.colorPrimary));
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		mScrollState = state;
		if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
			setCurrentTab(mCurrentPage);
		}
		if (mListener != null) {
			mListener.onPageScrollStateChanged(state);
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		mCurrentPage = position;
		mPositionOffset = positionOffset;
		invalidate();
		if (mListener != null) {
			mListener.onPageScrolled(position, positionOffset,
					positionOffsetPixels);
		}
	}

	@Override
	public void onPageSelected(int position) {
		if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
			mCurrentPage = position;
			mPositionOffset = 0;
		}
		if (mListener != null) {
			mListener.onPageSelected(position);
		}
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mViewPager == null) {
			return;
		}
		final int count = mViewPager.getAdapter().getCount();
		if (count == 0) {
			return;
		}
		if (mCurrentPage >= count) {
			setCurrentTab(count - 1);
			return;
		}
		final int paddingLeft = getPaddingLeft();
		final float pageWidth = (getWidth() - paddingLeft - getPaddingRight()) / (1f * count);
		final float pageHeight = getHeight() - getPaddingBottom() - getPaddingTop();
		final float left = paddingLeft + pageWidth * (mCurrentPage + mPositionOffset);
		final float right = left + pageWidth;
		final float bottom = getHeight() - getPaddingBottom();
		canvas.drawRect(left + pageWidth * (1 - indicatorW), bottom - pageHeight * indicatorH, right - pageWidth * (1 - indicatorW), bottom, mPaint);
	}
	
	public void setOnPageChangeListener(OnPageChangeListener listener) {
		mListener = listener;
	}

	@SuppressWarnings("deprecation")
	public void setViewPager(ViewPager view) {
		if (mViewPager == view) {
			return;
		}
		if (mViewPager != null) {
			mViewPager.setOnPageChangeListener(null);
		}
		if (view.getAdapter() == null) {
			return;
		}
		mViewPager = view;
		mViewPager.addOnPageChangeListener(this);
		initTabs();
	}

	public void initTabs() {
		removeAllViews();
		mTitles.clear();
		final PagerAdapter adapter = mViewPager.getAdapter();
		final int count = adapter.getCount();
		for (int i = 0; i < count; i++) {
			CharSequence title = adapter.getPageTitle(i);
			if (title == null) {
				title = "DEFAULT";
			}
			addTab(i, title);
		}
		setCurrentTab(mViewPager.getCurrentItem());
		requestLayout();
	}

	private void addTab(int index, CharSequence text) {
		final TextView tabView = new TextView(getContext());
		LayoutParams params = new LayoutParams(0, MATCH_PARENT, 1);
		tabView.setTag(index);
		tabView.setFocusable(true);
		tabView.setText(text);
		tabView.setTextSize(TypedValue.COMPLEX_UNIT_SP, indicatorTextSize);
		tabView.setGravity(Gravity.CENTER);
		addView(tabView, params);
		tabView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setCurrentTab((int) v.getTag());
			}
		});
		mTitles.add(tabView);
	}

	private void setCurrentTab(int item) {
		if (mCurrentPage != item) {
			mCurrentPage = item;
			mViewPager.setCurrentItem(item);
		}
		for (TextView tv : mTitles) {
			tv.setTextColor(indicatorTextColor);
		}
		mTitles.get(mCurrentPage).setTextColor(indicatorTextChosenColor);
	}

}
