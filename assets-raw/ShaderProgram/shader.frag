#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
void main()
{
    float d = 1.0 - distance(vec2(0.5), v_texCoords);
    gl_FragColor = v_color * d * texture2D(u_texture, v_texCoords);
}