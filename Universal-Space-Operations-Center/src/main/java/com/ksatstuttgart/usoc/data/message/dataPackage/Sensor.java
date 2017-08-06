/*
 * The MIT License
 *
 * Copyright 2017 KSat Stuttgart e.V..
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ksatstuttgart.usoc.data.message.dataPackage;

import com.ksatstuttgart.usoc.data.message.Var;
import java.util.ArrayList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author valentinstarlinger
 */
public class Sensor {

    private SensorType type;
    private String sensorName;
    private ArrayList<Var> vars;
    
    private int numPoints;
    private double frequency;

    public Sensor() {
        vars = new ArrayList<>();
    }

    public Sensor(Sensor s) {
        this.type = s.getType();
        this.sensorName = s.getSensorName();
        this.numPoints = s.getNumPoints();
        this.frequency = s.getFrequency();

        vars = new ArrayList<>();
        for (Var variable : s.getVars()) {
            vars.add(new Var(variable));
        }
    }

    @XmlAttribute(name = "type")
    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    @XmlAttribute(name = "name")
    public String getSensorName() {
        if (sensorName == null) {
            return "";
        }
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    @XmlElement(name = "var")
    public ArrayList<Var> getVars() {
        return vars;
    }

    public void setVariables(ArrayList<Var> datapoints) {
        this.vars = datapoints;
    }

    public void addVariable(Var dataPoint) {
        vars.add(dataPoint);
    }

    @Override
    public String toString() {
        String s = "Sensor name: " + this.getSensorName() + " (Type: " + this.type + ")\n";
        for (Var var : this.getVars()) {
            s += "\t"+var.toString()+"\n";
        }
        return s;
    }
    
    public int getTotalDataLength(){
        int length = 0;
        for (Var var : vars) {
            length += var.getDataType().getLength();
        }
        return length;
    }
    
    @XmlAttribute (name = "sensorpoints")
    public int getNumPoints() {
        //return number of numpoints. miminum numPoints is 1
        return numPoints == 0 ? 1 : numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    @XmlAttribute (name = "sensorfrequency")
    public double getFrequency() {
        //if frequency is zero but is required, this will default to 1Hz
        return frequency == 0 ? 1 : frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
    
    /*
    * AUTOGENERATED hashCode and equals functions
    */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + Objects.hashCode(this.sensorName);
        hash = 71 * hash + Objects.hashCode(this.vars);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sensor other = (Sensor) obj;
        if (!Objects.equals(this.getSensorName(), other.getSensorName())) {
            return false;
        }
        if (this.getType() != other.getType()) {
            return false;
        }
        return Objects.equals(this.getVars(), other.getVars());
    }
}
