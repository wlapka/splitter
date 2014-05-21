/**
 * @author wlapka
 *
 * @created May 12, 2014 11:47:40 AM
 */
package net.thoiry.lapka.splitter.service;

import java.util.List;

/**
 * @author wlapka
 * 
 */
public interface Splitter<O, I> {

	List<I> split(O message);
}
