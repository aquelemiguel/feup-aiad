package agents;

import java.util.List;

import jade.lang.acl.ACLMessage;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import sajas.core.AID;

public class TAgent extends PlayerAgent {
	public TAgent(ContinuousSpace<Object> space, Grid<Object> grid, boolean isIGL) {
		super(space, grid, new GridPoint(25, 45), isIGL);
	}
	
	public void informTeammates(GridCell<CTAgent> enemy) {
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.setContent("ping");
		msg.addReceiver(new AID("T1@AIAD Source", true));
		//send(msg);
	}
	
	@Override
	public void checkSurroundings() {
		GridPoint pt = grid.getLocation(this);
		GridCellNgh<CTAgent> nghCreator = new GridCellNgh<CTAgent>(this.grid, pt, CTAgent.class, 1, 1);
		List<GridCell<CTAgent>> gridCells = nghCreator.getNeighborhood(true);
		
		for (GridCell<CTAgent> enemy : gridCells) {
			informTeammates(enemy);
		}
	}
}
