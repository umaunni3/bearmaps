/** A class that represents a path via pursuit curves. */
public class Path {
	public Point curr;
	public Point next;

    public Path(double x, double y) {
    	this.next = new Point(x, y);
    	this.curr = new Point(50, 100);
    }

    public void iterate(double dx, double dy) {
    	this.curr = this.next; // make a copy of this.next?
    	this.next = new Point(curr.x + dx, curr.y + dy);
    }

}
