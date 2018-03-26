package com.panhong.util.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.panhong.model.Auth_Function;

public class Tree {
	
	private List<Node> nodes = new LinkedList<>();
	
	private Node root=null;
	
	public Tree(List<Auth_Function> functions) {
		
		for(Auth_Function function:functions){
			Node node=new Node(function.getId(), function.getParentId(),function.getName(), "open", new NodeAttribute(null==function.getUrl()?"":function.getUrl(),	function.getId()),
					function.getSerialNum());
			nodes.add(node);
			if(node.getId()==0){
				root=node;
			}
		}
	}
	
	public List<Node> build(){
		buildTree(root);
		List<Node> result=new ArrayList<Node>();
		result.add(root);
		return result;
	}

	private void buildTree(Node parent) {
		Node node=null;
		for(Iterator<Node> ite=nodes.iterator();ite.hasNext();){
			node=ite.next();
			if(Objects.equals(node.getParentId(),parent.getId())){
				parent.getChildren().add(node);
				buildTree(node);
			}
		}
		
	}

}
