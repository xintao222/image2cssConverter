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
package com.rf1m.image2css.cli;

import com.rf1m.image2css.cmn.domain.CssClass;
import com.rf1m.image2css.out.Output;
import com.rf1m.image2css.out.ReportOutput;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class CommandLineRunnerOutputManager {
    protected final Output consoleOutput;
    protected final Output cssOutput;
    protected final Output htmlOutput;

    protected final PrintStream printStream;

    protected final ReportOutput reportOutput;
    protected final String aboutProject;

    public CommandLineRunnerOutputManager(final Output consoleOutput, final Output cssOutput, final Output htmlOutput,
                                          final ReportOutput reportOutput, final PrintStream printStream, final String aboutProject) {

        this.consoleOutput = consoleOutput;
        this.cssOutput = cssOutput;
        this.htmlOutput = htmlOutput;
        this.reportOutput = reportOutput;
        this.printStream = printStream;
        this.aboutProject = aboutProject;
    }

    protected void doOutput(final Parameters parameters, final List<CssClass> cssClasses) throws IOException {
        if(parameters.isOutputToConsoleDesired()) {
            consoleOutput.out(parameters, cssClasses);
        }

        if(parameters.isCssFileOutputDesired()) {
            cssOutput.out(parameters, cssClasses);
        }

        if(parameters.isHtmlFileOutputDesired()) {
            htmlOutput.out(parameters, cssClasses);
        }

        if(parameters.isOutputToConsoleDesired()) {
            reportOutput.generateReportOutput(parameters, cssClasses);
        }
    }

    protected void showAbout() {
        printStream.println(aboutProject);
    }

}