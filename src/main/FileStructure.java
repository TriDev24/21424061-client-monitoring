/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configs;

import java.io.File;
import java.io.Serializable;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author bin
 */
public class FileStructure implements TreeModel, Serializable {
    
	protected File root;
	public FileStructure(File root) { this.root = root; }

	@Override
	public Object getRoot() {
            return root;
	}

	@Override
	public Object getChild(Object parent, int index) {
            String[] children = ((File)parent).list();
	    if ((children == null) || (index >= children.length)) return null;
	    return new File((File) parent, children[index]);
	}

	@Override
	public int getChildCount(Object parent) {
            String[] children = ((File)parent).list();
	    if (children == null) return 0;
	    return children.length;
	}

	@Override
	public boolean isLeaf(Object node) {
		return ((File)node).isFile();
	}


	@Override
	public int getIndexOfChild(Object parent, Object child) {
		String[] children = ((File)parent).list();
	    if (children == null) return -1;
	    String childname = ((File)child).getName();
	    for(int i = 0; i < children.length; i++) {
	      if (childname.equals(children[i])) return i;
	    }
	    return -1;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
	}

        @Override
        public void valueForPathChanged(TreePath path, Object newValue) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
}
