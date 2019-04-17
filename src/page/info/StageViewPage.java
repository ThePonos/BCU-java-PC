package page.info;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import page.JBTN;
import page.Page;
import util.stage.MapColc;
import util.stage.Stage;
import util.stage.StageMap;

public class StageViewPage extends StagePage {

	private static final long serialVersionUID = 1L;

	private final JList<MapColc> jlmc = new JList<>();
	private final JScrollPane jspmc = new JScrollPane(jlmc);
	private final JList<StageMap> jlsm = new JList<>();
	private final JScrollPane jspsm = new JScrollPane(jlsm);
	private final JList<Stage> jlst = new JList<>();
	private final JScrollPane jspst = new JScrollPane(jlst);
	private final JBTN cpsm = new JBTN(0, "cpsm");
	private final JBTN cpst = new JBTN(0, "cpst");

	public StageViewPage(Page p, Collection<MapColc> collection) {
		super(p);
		jlmc.setListData(new Vector<>(collection));

		ini();
		resized();
	}

	public StageViewPage(Page p, Collection<MapColc> col, Stage st) {
		this(p, col);
		if (st == null)
			return;
		jlmc.setSelectedValue(st.map.mc, true);
		jlsm.setSelectedValue(st.map, true);
		jlst.setSelectedValue(st, true);
	}

	@Override
	protected void resized(int x, int y) {
		super.resized(x, y);
		set(jspsm, x, y, 50, 100, 300, 1000);
		set(jspmc, x, y, 400, 100, 300, 400);
		set(jspst, x, y, 400, 550, 300, 550);
		set(cpsm, x, y, 50, 1150, 300, 50);
		set(cpst, x, y, 400, 1150, 300, 50);
	}

	@Override
	protected void setData(Stage st) {
		super.setData(st);
		cpst.setEnabled(st != null);
	}

	private void addListeners() {

		jlmc.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (arg0.getValueIsAdjusting())
					return;
				MapColc mc = jlmc.getSelectedValue();
				if (mc == null)
					return;
				jlsm.setListData(mc.maps);
				jlsm.setSelectedIndex(0);
			}

		});

		jlsm.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (arg0.getValueIsAdjusting())
					return;
				StageMap sm = jlsm.getSelectedValue();
				cpsm.setEnabled(false);
				if (sm == null)
					return;
				cpsm.setEnabled(true);
				jlst.setListData(new Vector<>(sm.list));
				jlst.setSelectedIndex(0);
			}

		});

		jlst.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (arg0.getValueIsAdjusting())
					return;
				Stage s = jlst.getSelectedValue();
				cpst.setEnabled(false);
				if (s == null)
					return;
				setData(s);
			}

		});

		cpsm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StageMap sm = jlsm.getSelectedValue();
				if (sm == null)
					return;
				MapColc mc = Stage.clipmc;
				StageMap copy = sm.copy(mc);
				mc.maps = Arrays.copyOf(mc.maps, mc.maps.length);
				mc.maps[mc.maps.length - 1] = copy;
			}
		});

		cpst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Stage stage = jlst.getSelectedValue();
				if (stage == null)
					return;
				Stage.clipsm.add(stage.copy(Stage.clipsm));
			}
		});

	}

	private void ini() {
		add(jspmc);
		add(jspsm);
		add(jspst);
		add(cpsm);
		add(cpst);
		cpsm.setEnabled(false);
		cpst.setEnabled(false);
		addListeners();
	}

}