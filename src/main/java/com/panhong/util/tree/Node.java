package com.panhong.util.tree;

import java.util.LinkedList;
import java.util.List;

public class Node implements Comparable<Node> {
	
	private int id;
	private int parentId;
	private String text;
	private String state;
	private NodeAttribute attributes;
	private List<Node> children=new LinkedList<>();
	private Integer order;
	
	

	public Node(int id, int parentId, String text, String state,NodeAttribute attributes, Integer order) {
		this.id = id;
		this.parentId = parentId;
		this.text = text;
		this.state = state;
		this.attributes = attributes;
		this.order = order;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return 0;
	}



	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public NodeAttribute getAttributes() {
		return attributes;
	}

	public void setAttributes(NodeAttribute attributes) {
		this.attributes = attributes;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}
