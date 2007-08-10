/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.ui.internal.provisional.views.markers;

import java.util.Map;

import org.eclipse.core.resources.IMarker;

/**
 * A MarkerFieldFilter is a filter on a particular marker field.
 * @since 3.4
 *
 */
public abstract class MarkerFieldFilter {
	
	private MarkerField field;

	/**
	 * Return whether or not marker should be filtered by the receiver.
	 * @param marker
	 * @return boolean <code>true</code> if the marker should be shown.
	 */
	public abstract boolean select(IMarker marker);
	
	/**
	 * Initialise the receiver with the values in the values Map.
	 * @param values
	 * @see FiltersContributionParameters
	 */
	public void initialize(Map values){
		//Do nothing by default
	}
	
	/**
	 * Populate the working copy with the copy of whatever fields are required.
	 * @param copy
	 */
	public void populateWorkingCopy(MarkerFieldFilter copy){
		copy.field = this.field;
	}

	/**
	 * Set the field for the receiver.
	 * @param markerField
	 */
	public final void setField(MarkerField markerField) {
		field = markerField;
		
	}

	/**
	 * Get the field for the receiver.
	 * @return MarkerField
	 */
	public final MarkerField getField() {
		return field;
	}
}
