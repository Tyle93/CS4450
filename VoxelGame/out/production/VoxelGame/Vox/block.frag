#version 120

uniform sampler2D texture1;

void main() {
    gl_FragColor = texture2D(texture1, gl_texCoord[0].st);
}
