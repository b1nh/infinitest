/*
 * This file is part of Infinitest.
 *
 * Copyright (C) 2010
 * "Ben Rady" <benrady@gmail.com>,
 * "Rod Coffin" <rfciii@gmail.com>,
 * "Ryan Breidenbach" <ryan.breidenbach@gmail.com>, et al.
 *
 * Infinitest is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Infinitest is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Infinitest.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.infinitest.eclipse.markers;

import java.util.*;

import org.eclipse.core.resources.*;
import org.infinitest.eclipse.workspace.*;
import org.infinitest.testrunner.*;

public class StackTracePlacementStrategy implements MarkerPlacementStrategy {
	private final ResourceLookup lookup;

	public StackTracePlacementStrategy(ResourceLookup lookup) {
		this.lookup = lookup;
	}

	public MarkerPlacement getPlacement(TestEvent event) {
		for (StackTraceElement element : event.getStackTrace()) {
			List<IResource> resources = lookup.findResourcesForClassName(element.getClassName());
			if (!resources.isEmpty()) {
				return new MarkerPlacement(resources.get(0), element.getLineNumber());
			}
		}
		return null;
	}
}
