/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.tablesaw.plotly;

import org.junit.Ignore;
import org.junit.Test;
import tech.tablesaw.api.NumberColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.components.Symbol;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.selection.Selection;

import java.io.IOException;

/**
 *
 */
@Ignore
public class BubbleExample {

    private final double[] x = {1, 2, 3, 4, 5, 6};
    private final double[] y = {0, 1, 6, 14, 25, 39};
    private final double[] size = {10, 33, 21, 40, 28, 16};


    @Test
    public void test1() throws IOException {
        Table marketShare = Table.read().csv("../data/market_share.csv");
        Table sub = marketShare.where(Selection.withRange(0, 4));
        NumberColumn x = sub.nCol("Products");
        NumberColumn y = sub.nCol("Sales");
        NumberColumn data = sub.nCol("Market_Share");

        Layout layout = Layout.builder().title("Market Share").build();
        Marker marker = Marker.builder().size(data).build();
        ScatterTrace trace = ScatterTrace
                .builder(x, y)
                .marker(marker)
                .build();

        Plot.show(new Figure(layout, trace));
    }

    @Test
    void testAsJavascript() {
        ScatterTrace trace = ScatterTrace.builder(x, y)
                .marker(Marker.builder().size(size).build())
                .build();
        System.out.println(trace.asJavascript(1));
    }

    @Test
    void showScatter() {

        ScatterTrace trace = ScatterTrace.builder(x, y)
                .mode(ScatterTrace.Mode.MARKERS)
                .marker(
                        Marker.builder()
                                .size(size)
                                .colorScale(Marker.Palette.CIVIDIS)
                                .opacity(.5)
                                .showScale(true)
                                .symbol(Symbol.DIAMOND_TALL)
                                .build())
                .build();

        Plot.show( new Figure(trace));
    }
}