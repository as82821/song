package service;

import java.util.List;

import model.Review;

public class ReviewListView {
	private int reviewTotalCount;
	private int currentPageNumber;
	public List<Review> reviewlist;
	private int pageTotalCount;
	private int reviewCountPerPage;
	private int firstRow;
	private int endRow;
	private int contentid;

	public ReviewListView(int reviewTotalCount, int currentPageNumber, List<Review> reviewlist, int reviewCountPerPage,
			int firstRow, int endRow,int contentid) {
		super();
		this.reviewTotalCount = reviewTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.reviewlist = reviewlist;
		this.reviewCountPerPage = reviewCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		this.contentid=contentid;

		calculatePageTotalCount();
	}

	private void calculatePageTotalCount() {
		if (reviewTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = reviewTotalCount / reviewCountPerPage;
			if (reviewTotalCount % reviewCountPerPage > 0) {
				pageTotalCount++;
			}
		}

	}

	public int getReviewTotalCount() {
		return reviewTotalCount;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public List<Review> getReviewlist() {
		return reviewlist;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public int getReviewCountPerPage() {
		return reviewCountPerPage;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public int getEndRow() {
		return endRow;
	}
	
	public int getContentid() {
		return contentid;
	}

	public boolean isEmpty() {
		return reviewTotalCount == 0;
	}
}
