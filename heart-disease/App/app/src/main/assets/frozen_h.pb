
G
inputs/inputsPlaceholder*
dtype0*
shape:���������

�
weights/hidden_weightsConst*
dtype0*�
value�B�
"��^�=}�%���=[���e>�����>�e�C>�x��T�������=��H>}/��ɼ�N��Z>�mh>_�	��턽�l�=D{$�F1>�S�=z�l�>�+ҽ:0������<�A�������[>���fw�B2q�9P>=�3�>k����?"�aUm=�7�*�k�7��� ?�g�;Q�+I�>�d�բ�
s
weights/hidden_weights/readIdentityweights/hidden_weights*
T0*)
_class
loc:@weights/hidden_weights
o
weights/output_weightsConst*
dtype0*A
value8B6"(�t>Y��<����	b=����>m!�U Խ��>y���
s
weights/output_weights/readIdentityweights/output_weights*
T0*)
_class
loc:@weights/output_weights
U
biases/hidden_biasesConst*
dtype0*)
value B"(M=_vO<׵�=    �o>
m
biases/hidden_biases/readIdentitybiases/hidden_biases*
T0*'
_class
loc:@biases/hidden_biases
I
biases/output_biasesConst*
dtype0*
valueB"ab�>bb��
m
biases/output_biases/readIdentitybiases/output_biases*
T0*'
_class
loc:@biases/output_biases
y
hidden_layers/MatMulMatMulinputs/inputsweights/hidden_weights/read*
T0*
transpose_a( *
transpose_b( 
q
hidden_layers/BiasAddBiasAddhidden_layers/MatMulbiases/hidden_biases/read*
data_formatNHWC*
T0
I
!hidden_layers/hidden_layer_outputReluhidden_layers/BiasAdd*
T0
�
predictions/MatMulMatMul!hidden_layers/hidden_layer_outputweights/output_weights/read*
T0*
transpose_a( *
transpose_b( 
l
predictions/logitsBiasAddpredictions/MatMulbiases/output_biases/read*
data_formatNHWC*
T0
?
predictions/predictionsSoftmaxpredictions/logits*
T0 