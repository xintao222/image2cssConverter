/**
 *
 * Copyright (c) 2011 Matthew D Huckaby. All rights reservered.
 * ------------------------------------------------------------------------------------
 * Image2Css is licensed under Apache 2.0, please see LICENSE file.
 *
 * Use of this software indicates you agree to the following as well :
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * This product includes software developed by The Apache Software Foundation (http://www.apache.org/).
 * ------------------------------------------------------------------------------------
 */
package com.rf1m.image2css.out;

import com.rf1m.image2css.cli.Parameters;
import com.rf1m.image2css.domain.CssClass;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static java.lang.String.format;

public class ConsoleOutput extends AbstractOutput implements ReportOutput{
    protected static final String REPORT_CSS_TOTAL 	    = "Generated [%1$s] CSS entries";
    protected static final String REPORT_CSS_FILE 	    = "Created CSS file, %1$s";
    protected static final String REPORT_HTML_FILE 	    = "Created HTML file, %1$s";

    protected final PrintStream consoleOut;

    public ConsoleOutput(){
        this.consoleOut = System.out;
    }

    @Override
    public void out(final Parameters parameters, final List<CssClass> cssClasses) throws IOException {
        if(isValidParametersAndClassesWithConsoleOutput(parameters, cssClasses)){
			for(final CssClass cssClass : cssClasses){
				consoleOut.println(cssClass.getBody());
			}
		}
    }

    @Override
    public void generateReportOutput(final Parameters parameters, final List<CssClass> cssClasses){
        if(super.isValidParametersAndClasses(parameters, cssClasses)){
            consoleOut.println(format(REPORT_CSS_TOTAL, cssClasses.size()));

            if(null != parameters.getCssFile()){
                consoleOut.println(format(REPORT_CSS_FILE, parameters.getCssFile().getName()));
            }

            if(null != parameters.getHtmlFile()){
                consoleOut.println(format(REPORT_HTML_FILE, parameters.getHtmlFile().getName()));
            }
        }
	}

    protected boolean isValidParametersAndClassesWithConsoleOutput(final Parameters parameters, final List<CssClass> cssClasses) {
        return
            super.isValidParametersAndClasses(parameters, cssClasses) &&
            parameters.isOutputToConsoleDesired();
    }

}
