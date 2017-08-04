/**
 * @author loc.mh
 */
package vn.credit.home.util.param;

import java.util.List;

/**
 * @author loc.mh
 *
 */
public class DataTablesModel {
	public int draw;
	public int start;
	public int length;
	public List<Column> columns;
	public Search search;
	public List<Order> order;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

}
