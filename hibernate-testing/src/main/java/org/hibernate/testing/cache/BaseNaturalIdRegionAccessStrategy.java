/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.testing.cache;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;

/**
 * @author Eric Dalquist
 */
class BaseNaturalIdRegionAccessStrategy extends BaseRegionAccessStrategy implements NaturalIdRegionAccessStrategy {
	private final NaturalIdRegionImpl region;

	@Override
	protected BaseGeneralDataRegion getInternalRegion() {
		return region;
	}

	@Override
	protected boolean isDefaultMinimalPutOverride() {
		return region.getSettings().isMinimalPutsEnabled();
	}

	@Override
	public NaturalIdRegion getRegion() {
		return region;
	}

	@Override
	public boolean insert(Object key, Object value) throws CacheException {
		return putFromLoad( key, value, 0, null );
	}

	@Override
	public boolean afterInsert(Object key, Object value) throws CacheException {
		return false;
	}

	@Override
	public boolean update(Object key, Object value) throws CacheException {
		return putFromLoad( key, value, 0, null );
	}

	@Override
	public boolean afterUpdate(Object key, Object value, SoftLock lock) throws CacheException {
		return false;
	}

	BaseNaturalIdRegionAccessStrategy(NaturalIdRegionImpl region) {
		this.region = region;
	}
}
