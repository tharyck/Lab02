package com.produto.utils;

/**
 * Interface to Generic GenericService. All models must implement it.
 *
 * @param <ID>
 */
public interface Model<ID> {
	ID getId();
	void setId(ID id);
}
