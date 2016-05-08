package lin.jiang.notice.presentation.base;

/**
 * @author YangLinjiang
 * @date 2015-11-9上午11:45:02
 * @description ViewPager的fragment, 建议ViewPager.setOffscreenPageLimit最大
 */
public abstract class BaseViewPagerFragment extends BaseFragment {
	@Override
	protected void InitData() {}

	private boolean isFirst = true;
	/**
	 * 当当前页显示的时候调用, 调用时期在OnCreateView()方法之前，请注意
	 * @param isFirst 为true时，表示第一次显示在当前页面，之后为false
	 */
	protected abstract void InitData(boolean isFirst);
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && isFirst) {
			InitData(true);
			isFirst = false;
		} else if (isVisibleToUser) {
			InitData(false);
		}
	}
}