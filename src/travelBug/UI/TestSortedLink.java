package travelBug.UI;

import static org.junit.Assert.isArray;

import java.util.Iterator;
import java.util.LinkedList;

import travelBug.library.LinkArray;
import travelBug.library.ReadWriteFile;
import travelBug.library.SortedLinkedList;
import travelBug.library.singlyLinkedList;
import travelBug.obj.TravelLeg;
import travelBug.obj.TravelLegInfo;

public class TestSortedLink {
public static void main(String[] args) {
	LinkArray<TravelLegInfo> tArray = new LinkArray<TravelLegInfo>();
	ReadWriteFile<TravelLegInfo> tFile = new ReadWriteFile<TravelLegInfo>("TravelLeg.txt", TravelLegInfo.class);
	tArray = tFile.readLinkArray();
	singlyLinkedList<TravelLeg> rArray = new singlyLinkedList<TravelLeg>();
	SortedLinkedList<TravelLeg> sArray = new SortedLinkedList<TravelLeg>();	
	
	for(int i = 1; i <= tArray.size(); i ++) {
		rArray.add(new TravelLeg(tArray.getIndexElement(i - 1).getMode(), tArray.getIndexElement(i - 1).getSource(), tArray.getIndexElement(i - 1).getDest(), tArray.getIndexElement(i - 1).getPrice(), tArray.getIndexElement(i - 1).getDistance(), tArray.getIndexElement(i - 1).getfromDate(), tArray.getIndexElement(i - 1).gettoDate(), tArray.getIndexElement(i - 1).getfromTime(), tArray.getIndexElement(i - 1).gettoTime()));
		System.out.println(rArray.getEntry(i).getSource() + " " + rArray.getEntry(i).getPrice());
	}
	
		sArray.addAll(rArray);
		sArray.remove(2);
		for(int i = 1;i <= sArray.getLength();i++) {
			System.out.println(sArray.getEntry(i).getSource() + " " + sArray.getEntry(i).getPrice());
		}
		System.out.print(sArray.getLastNode().getPrice());

}
}
