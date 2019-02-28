package cn.maaa.common.utils;
import cn.maaa.common.domain.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildTree {

    /**
     * 单顶级节点树
     * @param List<Tree<T>> nodes
     * @return
     */
	public static <T> Tree<T> build(List<Tree<T>> nodes) {

		List<Tree<T>> topNodes = buildList(nodes, "0");

		Tree<T> root = new Tree<T>();
		if (topNodes.size() == 1) {
			root = topNodes.get(0);
		} else {
			root.setId("-1");
			root.setParentId("");
			root.setHasParent(false);
			root.setHasChildren(true);
			root.setChecked(true);
			root.setChildren(topNodes);
			root.setText("顶级节点");
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			root.setState(state);
		}

		return root;
	}


	/**
	 *  多顶级节点树
	 * @param List<Tree<T>> nodes, String idParam
	 * @return
	 */
	public static <T> List<Tree<T>> buildList(List<Tree<T>> nodes, String idParam) {
		if (nodes == null) {
			return null;
		}
		List<Tree<T>> topNodes = new ArrayList<Tree<T>>();

		for (Tree<T> children : nodes) {

			String pid = children.getParentId();
			//顶级节点直接跳过
			if (pid == null || idParam.equals(pid)) {
				topNodes.add(children);

				continue;

			}

			//子节点查找父节点
			for (Tree<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setHasChildren(true);

				   //	continue;
					break;//此处更改为break跳出循环
				}
			}

		}
		return topNodes;
	}

}