/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.internal;

import org.eclipse.ui.*;
import org.eclipse.ui.help.WorkbenchHelp;
import org.eclipse.ui.internal.IHelpContextIds;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.*;

/**
 * Sets the current perspective of the workbench
 * page.
 */
public class SetPagePerspectiveAction extends Action {
	private WorkbenchPage page;
	private IPerspectiveDescriptor persp;
	
	/**
	 *	Create an instance of this class
	 */
	public SetPagePerspectiveAction(IPerspectiveDescriptor perspective, WorkbenchPage workbenchPage) {
		super(WorkbenchMessages.getString("SetPagePerspectiveAction.text")); //$NON-NLS-1$
		setChecked(false);
		persp = perspective;
		page = workbenchPage;
		update(persp);
		WorkbenchHelp.setHelp(this, IHelpContextIds.SWITCH_TO_PERSPECTIVE_ACTION);
	}

	/**
	 * Returns the page this action applies to
	 */
	/* package */ WorkbenchPage getPage() {
		return page;
	}
	
	/**
	 * Returns the perspective this action applies to
	 */
	/* package */ IPerspectiveDescriptor getPerspective() {
		return persp;
	}
	
	/**
	 * Returns whether this action handles the specified
	 * workbench page and perspective.
	 */
	public boolean handles(IPerspectiveDescriptor perspective, WorkbenchPage workbenchPage) {
		return persp == perspective && page == workbenchPage;
	}
	
	/**
	 * Replaces the perspective used
	 */
	public void setPerspective(IPerspectiveDescriptor newPerspective) {
		persp = newPerspective;
	}
	
	/**
	 * The user has invoked this action
	 */
	public void run() {
		page.setPerspective(persp);
		// Force the button into proper checked state
		setChecked(page.getPerspective() == persp);
	}
	
	/**
	 *	Update the action.
	 */
	public void update(IPerspectiveDescriptor newDesc) {
		persp = newDesc;
		setToolTipText(WorkbenchMessages.format("SetPagePerspectiveAction.toolTip", new Object[] {persp.getLabel()})); //$NON-NLS-1$
		ImageDescriptor image = persp.getImageDescriptor();
		if (image != null) {
			setImageDescriptor(image);
			setHoverImageDescriptor(null);
		} else {
			setImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_CTOOL_DEF_PERSPECTIVE));
			setHoverImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_CTOOL_DEF_PERSPECTIVE_HOVER));
		}
		setText(persp.getLabel());
	}
	
	/**
	 * Return whether or not this actions shows text in the toolbar.
	 * @return
	 */
	public boolean showTextInToolBar(){
		return true;
	}
	
}
