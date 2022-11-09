package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

import java.util.*;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }


    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        Queue<JigsawNode> openList = new LinkedList<JigsawNode>(){
            private static final long serialVersionUID = 1L;
        };  //已发现但未保存的节点
        Set<JigsawNode> closeList = new HashSet<>(1000); //已保存的节点

        openList.add(bNode);
        closeList.add(bNode);

        while(!openList.isEmpty()){
            //将openList第一个节点置为currentJNode
            if (openList.peek().equals(eNode)){
                this.currentJNode=openList.peek();
                this.getPath();
                //calSolutionPath();

                System.out.println(getSearchedNodesNum());
                System.out.println(getSolutionPath());
                return true;
            }

            //	寻找所有与currentJNode邻接且未曾被发现的节点，将它们按代价估值从小到大排序插入exploreList中
            //	并加入visitedList中，表示已发现
            for (int i = 0; i < 4; i++) {
                JigsawNode temp = new JigsawNode(openList.peek());
                if (temp.move(i)) {
                    if (!closeList.contains(temp)){
                        openList.add(temp);
                        closeList.add(temp);
                    }
                }
            }

            /**
            else{
                Vector<JigsawNode> child = findFollowJNodes(currentJNode,openList,closeList);
                for (JigsawNode ch : child) {
                    openList.add(ch);
                }
                openList.remove(currentJNode);
                setOpenList(openList);

                closeList.add(currentJNode);
                setCloseList(closeList);

                currentJNode = openList.peek();
                int num=getSearchedNodesNum();
                setSearchedNodesNum(num++);
            }
             */

        }

        return false;
    }


    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int s = 0; // 后续节点不正确的数码个数
        int manHattenDis=0; //曼哈顿距离
        int geometricDis=0; //欧几里得距离
        int err=0; //当前节点不正确数码个数

        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            int x1=(index-1)/dimension +1;
            int y1=index % dimension ==0? dimension : index % dimension;
            int x2=(jNode.getNodesState()[index]-1)/dimension +1;
            int y2=jNode.getNodesState()[index] % dimension==0?
                    dimension : jNode.getNodesState()[index] % dimension;
            int dx=Math.abs(x1-x2);
            int dy=Math.abs(y1-y2);

            manHattenDis += dx+dy;
            geometricDis +=Math.sqrt(dx*dx + dy*dy);

            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
            if (jNode.getNodesState()[index]!=index){
                err++;
            }
        }

        //权重是1：1：1
        s = (int)(s + manHattenDis + geometricDis);
        jNode.setEstimatedValue(s);
    }
}
