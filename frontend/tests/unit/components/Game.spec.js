import { shallowMount } from '@vue/test-utils';
import Game from '@/components/Game';

// see https://vue-test-utils.vuejs.org/guides/using-with-vue-router.html#testing-components-that-use-router-link-or-router-view
describe('Game component should', () => {
    it('render without crashing', () => {
        const wrapper = shallowMount(Game, {
            stubs: ['router-link', 'router-view']
        });
        expect(wrapper.find('game')).toBeDefined();
    });
});
