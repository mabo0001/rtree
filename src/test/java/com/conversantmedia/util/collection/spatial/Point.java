package com.conversantmedia.util.collection.spatial;

/*
 * #%L
 * Conversant RTree
 * ~~
 * Conversantmedia.com © 2016, Conversant, Inc. Conversant® is a trademark of Conversant, Inc.
 * ~~
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
 * #L%
 */

/**
 * Created by jcovert on 6/15/15.
 */
public class Point implements HyperPoint {
    final double x, y;

    Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getNDim() {
        return 2;
    }

    @Override
    public Double getCoord(final int d) {
        if(d==0) {
            return x;
        } else if(d==1) {
            return y;
        } else {
            throw new IllegalArgumentException("Invalid dimension");
        }
    }

    @Override
    public double distance(final HyperPoint p) {
        final Point p2 = (Point)p;

        final double dx = p2.x-x;
        final double dy = p2.y-y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    @Override
    public double distance(final HyperPoint p, final int d) {
        final Point p2 = (Point)p;
        if(d == 0) {
            return Math.abs(p2.x - x);
        } else if (d == 1) {
            return Math.abs(p2.y - y);
        } else {
            throw new IllegalArgumentException("Invalid dimension");
        }
    }

    public final static class Builder implements RectBuilder<Point> {

        @Override
        public HyperRect getBBox(final Point point) {
            return new Rect2D(point);
        }

        @Override
        public HyperRect getMbr(final HyperPoint p1, final HyperPoint p2) {
            final Point point1 = (Point)p1;
            final Point point2 = (Point)p2;
            return new Rect2D(point1, point2);
        }
    }
}