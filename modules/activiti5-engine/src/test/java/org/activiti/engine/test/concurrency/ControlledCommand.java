/* Licensed under the Apache License, Version 2.0 (the "License");
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

package org.activiti.engine.test.concurrency;

import org.activiti5.engine.impl.interceptor.Command;
import org.activiti5.engine.impl.interceptor.CommandContext;

/**
 * @author Tom Baeyens
 */
public class ControlledCommand implements Command<Object> {
  
  protected ControllableThread controllableThread; 
  protected Command<?> command;
  
  public ControlledCommand(ControllableThread controllableThread, Command<?> command) {
    this.controllableThread = controllableThread;
    this.command = command;
  }
  
  public Object execute(CommandContext commandContext) {
    Object result = command.execute(commandContext);
    controllableThread.returnControlToTestThreadAndWait();
    return result;
  }
}