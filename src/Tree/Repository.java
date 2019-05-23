package Tree;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import FileAndFolder.DataObject;
import Permissions.Permissions;


public class Repository{
    private List<Repository> children = new ArrayList<Repository>();
    private Repository parent = null;
    private DataObject data = null;
    private String type;
    

    public Repository(DataObject data, String type) {
        this.data = data;
        this.getData().setLastAccesedDate(new Date());
        this.setType(type);
    }
    public Repository(String name, String type) {
        this.data.setName(name);
        this.getData().setLastAccesedDate(new Date());
        this.setType(type);
    }
    public Repository(String name, Permissions permission, String type) {
        this.data.setName(name);
        this.data.setPermission(permission);
        this.getData().setLastAccesedDate(new Date());
        this.setType(type);
    }
    public Repository(String name, Repository parent, String type) {
        this.data.setName(name);
        this.parent = parent;
        parent.addChild(this);
        this.getData().setLastAccesedDate(new Date());
        this.setType(type);
    }
    public Repository(String name, Repository parent, Permissions permission, String type) {
        this.data.setName(name);
        this.data.setPermission(permission);
        this.parent = parent;
        parent.addChild(this);
        this.getData().setLastAccesedDate(new Date());
        this.setType(type);
    }
    public Repository(){
    	
    }

    public Repository(DataObject data, Repository parent, String type) {
        this.data = data;
        this.parent = parent;
        this.getData().setLastAccesedDate(new Date());
        parent.getData().setLastAccesedDate(new Date());
        parent.addChild(this);
        this.setType(type);
    }
    
    public List<Repository> getChildren() {
        return children;
    }

    public void setParent(Repository parent) {
    	this.parent = parent;
    	Double dim = parent.getDimension();
    	Double this_dim = this.getDimension();
    	parent.getData().setDimension(dim + this_dim);
    	parent.addChild(this);
    	
    	this.setPermission(parent.getPermission());
    	
    	
    }
    public Repository getParent(){
    	return this.parent;
    }

//    public void addChild(DataObject data) {
//        Repository child = new Repository(data);
//        this.children.add(child);
//        this.getData().setLastAccesedDate(new Date());
//        //child get permission from parent ( data will be of type info) maybe
//    }

    public void addChild(Repository child) {
        this.children.add(child);
        this.getData().setLastAccesedDate(new Date());
    }

    public DataObject getData() {
        return this.data;
    }

    public void setData(DataObject data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        if(this.children.size() == 0) 
            return true;
        else 
            return false;
    }
    public void removeChild(Repository node){
//    	Iterator<Node> iter =  this.getChildren().iterator();
//    	
//    		
//    		if(iter == node){
//    			iter.remove();
//    			break;
//    		}
//    		iter.next();
//    	}
    	this.getData().setLastAccesedDate(new Date());
    	this.getChildren().remove(node);
//    	if(!node.isLeaf()){
//    		Iterator<Node> iter =  node.getChildren().iterator();
//    		while(iter.hasNext()){
//    			this.removeChild(iter.next());
//    		}
//    	}
    	node.removeParent();
    	
    }
    public void removeParent() {
        this.parent = null;
    }

	public String getName() {
		return this.getData().getName();
	}

	public Double getDimension() {
		return this.getData().getDimension();
	}

	public void setDimension(Double dimension) {
		this.getData().setDimension(dimension);
		
	}

	public Permissions getPermission() {
		return this.getData().getPermission();
	}

	public void setPermission(Permissions permission) {
		this.getData().setPermission(permission);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}