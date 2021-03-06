/*******************************************************************************
 * ===========================================================
 * Ankush : Big Data Cluster Management Solution
 * ===========================================================
 * 
 * (C) Copyright 2014, by Impetus Technologies
 * 
 * This is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License (LGPL v3) as
 * published by the Free Software Foundation;
 * 
 * This software is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this software; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 ******************************************************************************/
package com.impetus.ankush2.ganglia;

import java.util.Collection;

import com.impetus.ankush2.constant.Constant;
import com.impetus.ankush2.framework.config.ClusterConfig;
import com.impetus.ankush2.framework.monitor.AbstractMonitor;

public class GangliaClusterMonitor extends AbstractMonitor {

	@Override
	public boolean canNodesBeDeleted(ClusterConfig clusterConfig,
			Collection<String> nodes, String componentName) {
		logger.setCluster(clusterConfig);
		String gangliaMaster = clusterConfig
				.getComponents()
				.get(Constant.Component.Name.GANGLIA)
				.getAdvanceConfStringProperty(
						GangliaConstants.ClusterProperties.GMETAD_HOST);
		System.out.println("gangliaMaster: " + gangliaMaster);
		if (nodes.contains(gangliaMaster)) {
			addAndLogError("Could not delete nodes as " + gangliaMaster
					+ " node is "
					+ GangliaConstants.Ganglia_Services.GangliaMaster);
			return false;
		}
		return true;
	}

}
