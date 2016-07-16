package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.CombinedDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.util.List;

/**
 * Created by Philipp Jahoda on 12/09/15.
 */
public class CombinedHighlighter extends ChartHighlighter<CombinedDataProvider> implements Highlighter {

	/**
	 * bar highlighter for supporting stacked highlighting
	 */
	protected BarHighlighter barHighlighter;

	public CombinedHighlighter(CombinedDataProvider chart, BarDataProvider barChart) {
		super(chart);

		// if there is BarData, create a BarHighlighter
		barHighlighter = barChart.getBarData() == null ? null : new BarHighlighter(barChart);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List<Highlight> getHighlightsAtXPos(float xVal, float x, float y) {

		mHighlightBuffer.clear();

		List<BarLineScatterCandleBubbleData> dataObjects = mChart.getCombinedData().getAllData();

		for (int i = 0; i < dataObjects.size(); i++) {

			ChartData dataObject = dataObjects.get(i);

			// in case of BarData, let the BarHighlighter take over
			if (barHighlighter != null && dataObject instanceof BarData) {
				Highlight high = barHighlighter.getHighlight(x, y);

				if (high != null) {
					high.setDataIndex(i);
					mHighlightBuffer.add(high);
				}
			} else {

				for (int j = 0, dataSetCount = dataObject.getDataSetCount(); j < dataSetCount; j++) {

					IDataSet dataSet = dataObjects.get(i).getDataSetByIndex(j);

					// don't include datasets that cannot be highlighted
					if (!dataSet.isHighlightEnabled())
						continue;

					Highlight s1 = buildHighlight(dataSet, j, xVal, DataSet.Rounding.CLOSEST);
					s1.setDataIndex(i);
					mHighlightBuffer.add(s1);
				}
			}
		}

		return mHighlightBuffer;
	}

}
