package cn.com.se.sharepictrue.quickadapter;

public interface MultiViewTypeSupport<T>
{
	int getLayoutId(int position, T t);
	
	int getViewTypeCount();
	
	int getItemViewType(int position, T t);
}