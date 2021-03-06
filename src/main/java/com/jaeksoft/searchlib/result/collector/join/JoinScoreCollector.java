/**   
 * License Agreement for OpenSearchServer
 *
 * Copyright (C) 2012-2014 Emmanuel Keller / Jaeksoft
 * 
 * http://www.open-search-server.com
 * 
 * This file is part of OpenSearchServer.
 *
 * OpenSearchServer is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * OpenSearchServer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenSearchServer. 
 *  If not, see <http://www.gnu.org/licenses/>.
 **/

package com.jaeksoft.searchlib.result.collector.join;

import org.apache.commons.lang3.ArrayUtils;

import com.jaeksoft.searchlib.index.ReaderAbstract;
import com.jaeksoft.searchlib.result.collector.AbstractBaseCollector;
import com.jaeksoft.searchlib.result.collector.AbstractExtendsCollector;
import com.jaeksoft.searchlib.result.collector.ScoreInterface;
import com.jaeksoft.searchlib.util.array.FloatBufferedArray;

public class JoinScoreCollector extends
		AbstractExtendsCollector<JoinDocCollectorInterface, JoinDocCollector>
		implements JoinDocCollectorInterface, ScoreInterface {

	private final float maxScore;
	private final float[] scores;
	private final FloatBufferedArray scoresCollector;

	JoinScoreCollector(JoinDocCollector base) {
		super(base);
		maxScore = 0;
		scores = ScoreInterface.EMPTY_SCORES;
		scoresCollector = null;
	}

	JoinScoreCollector(JoinDocCollector base, ScoreInterface scoreDocInterface,
			int joinResultSize) {
		super(base);
		maxScore = scoreDocInterface.getMaxScore();
		scoresCollector = null;
		scores = ArrayUtils.clone(scoreDocInterface.getScores());
	}

	private JoinScoreCollector(JoinDocCollector base, JoinScoreCollector src) {
		super(base);
		maxScore = src.maxScore;
		scoresCollector = null;
		scores = ArrayUtils.clone(src.scores);
	}

	JoinScoreCollector(final JoinDocCollector base, final int[] srcIds,
			final float[] srcScores) {
		super(base);
		scoresCollector = new FloatBufferedArray(srcIds.length);
		int i = 0;
		float msc = 0;
		for (int id : srcIds) {
			if (id != -1) {
				float score = srcScores[i];
				scoresCollector.add(score);
				if (score > msc)
					msc = score;
			}
			i++;
		}
		maxScore = msc;
		scores = scoresCollector.getFinalArray();
	}

	@Override
	public JoinScoreCollector duplicate(final AbstractBaseCollector<?> base) {
		parent.duplicate(base);
		return new JoinScoreCollector((JoinDocCollector) base, this);
	}

	@Override
	final public float[] getScores() {
		return scores;
	}

	@Override
	final public float getMaxScore() {
		return maxScore;
	}

	@Override
	final public void setForeignDocId(final int pos, final int joinResultPos,
			final int foreignDocId, final float foreignScore) {
		parent.setForeignDocId(pos, joinResultPos, foreignDocId, foreignScore);
		float score = this.scores[pos] * foreignScore;
		this.scores[pos] = score;
	}

	@Override
	final public void swap(final int pos1, final int pos2) {
		parent.swap(pos1, pos2);
		float score = scores[pos1];
		scores[pos1] = scores[pos2];
		scores[pos2] = score;
	}

	@Override
	final public int getSize() {
		if (scores == null)
			return 0;
		return scores.length;
	}

	@Override
	final public ReaderAbstract[] getForeignReaders() {
		return base.getForeignReaders();
	}

}
