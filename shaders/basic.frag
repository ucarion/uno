#version 330

precision highp float;

varying vec4 v_Color;

void main()
{
	gl_FragColor = v_Color;
}